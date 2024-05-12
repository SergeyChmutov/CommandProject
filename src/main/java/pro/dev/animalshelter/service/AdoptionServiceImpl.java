package pro.dev.animalshelter.service;

import org.springframework.stereotype.Service;
import pro.dev.animalshelter.enums.RequestStatus;
import pro.dev.animalshelter.exception.AdoptionNotFoundException;
import pro.dev.animalshelter.exception.AdoptionStatusChangesErrorException;
import pro.dev.animalshelter.interfaces.AdoptionService;
import pro.dev.animalshelter.interfaces.AnimalInterface;
import pro.dev.animalshelter.model.Adoption;
import pro.dev.animalshelter.model.Animal;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.Users;
import pro.dev.animalshelter.repository.AdoptionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static pro.dev.animalshelter.constant.Constants.*;

@Service
public class AdoptionServiceImpl implements AdoptionService {
    private final AdoptionRepository adoptionRepository;
    private final AnimalInterface animalService;
    private final TelegramBotSender telegramBotSender;

    public AdoptionServiceImpl(
            AdoptionRepository adoptionRepository,
            AnimalInterface animalService,
            TelegramBotSender telegramBotSender
    ) {
        this.adoptionRepository = adoptionRepository;
        this.animalService = animalService;
        this.telegramBotSender = telegramBotSender;
    }

    private static LocalDate getNewTrialPeriod(LocalDate currentTrialPeriodEndDate, int daysToAdd) {
        return currentTrialPeriodEndDate.plusDays(daysToAdd);
    }

    @Override
    public Adoption addAdoption(Long animalId, Long userId, Long shelterId) {
        Animal animal = new Animal();
        animal.setIdAnimal(animalId);
        Users user = new Users();
        user.setId(userId);
        Shelter shelter = new Shelter();
        shelter.setId(shelterId);

        Adoption adoption = new Adoption(shelter, user, animal);
        return adoptionRepository.save(adoption);
    }

    @Override
    public Adoption getAdoption(Long id) {
        return adoptionRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Adoption> getAdoption() {
        return adoptionRepository.findAll();
    }

    @Override
    public Adoption changeTrialPeriod(Long id, int daysToAdd) {
        Adoption adoption = adoptionRepository.findById(id)
                .orElseThrow(() -> new AdoptionNotFoundException("Не найдена запись об усыновлении с номером " + id));

        LocalDate currentTrialPeriodEndDate;
        if (adoption.getTrialDate() != null) {
            currentTrialPeriodEndDate = adoption.getTrialDate();
        } else {
            currentTrialPeriodEndDate = LocalDate.now();
        }
        adoption.setTrialDate(getNewTrialPeriod(currentTrialPeriodEndDate, daysToAdd));
        adoptionRepository.save(adoption);
        telegramBotSender.send(adoption.getUser().getId(), String.format(MESSAGE_CHANGE_TRIAL_PERIOD, daysToAdd));
        return adoption;
    }

    @Override
    public Adoption changeRequestStatus(Long id, RequestStatus status) {
        final Optional<Adoption> createdAdoption = adoptionRepository.findById(id);

        if (createdAdoption.isPresent()) {
            final Adoption adoption = createdAdoption.get();
            RequestStatus currentStatus = adoption.getStatus();
            checkNewStatusForCorrectness(currentStatus, status);
            adoption.setStatus(status);
            adoptionRepository.save(adoption);
            Animal animal = adoption.getAnimal();
            switch (status) {
                case APPROVED:
                    adoption.setTrialDate(getNewTrialPeriod(LocalDate.now(), ADOPTION_TRIAL_PERIOD_THIRTY_DAYS));
                    adoptionRepository.save(adoption);
                    animal.setShelter(null);
                    animalService.updateAnimal(animal);
                    break;
                case REJECTED:
                case REJECTED_DURING_TRIAL_DATE:
                    animal.setShelter(adoption.getShelter());
                    animalService.updateAnimal(animal);
                    break;
                default:
                    break;
            }
            sendResolutionMessageToUser(adoption.getUser().getId(), adoption.getId(), status);
            return adoption;
        } else {
            throw new AdoptionNotFoundException("Не найдена запись об усыновлении с номером " + id);
        }
    }

    @Override
    public void deleteAdoption(Long id) {
        adoptionRepository.deleteById(id);
    }

    @Override
    public Optional<Adoption> findAdoptionByUserIdAndStatus(Long userId, RequestStatus status) {
        return adoptionRepository.findByUser_IdAndStatus(userId, status);
    }

    @Override
    public Optional<Adoption> findAdoptionByUserIdAndStatusAndTrialDate(
            Long userId,
            RequestStatus status,
            LocalDate trialDate
    ) {
        return adoptionRepository.findByUser_IdAndStatusAndTrialDateLessThanEqual(userId, status, trialDate);
    }

    private void checkNewStatusForCorrectness(RequestStatus currentStatus, RequestStatus status) {
        Boolean newStatusIsCorrect = true;
        switch (currentStatus) {
            case CONSIDERATION:
                newStatusIsCorrect = status == RequestStatus.APPROVED
                        || status == RequestStatus.REJECTED;
                break;
            case APPROVED:
                newStatusIsCorrect = status == RequestStatus.REJECTED_DURING_TRIAL_DATE;
                break;
            case REJECTED:
            case REJECTED_DURING_TRIAL_DATE:
                newStatusIsCorrect = false;
            default:
                break;
        }

        if (!newStatusIsCorrect) {
            throw new AdoptionStatusChangesErrorException(
                    "Ошибка изменения статуса заявки на усыновление. Текущий статус: " + currentStatus
                            + ", новый статус: " + status
            );
        }
    }

    private void sendResolutionMessageToUser(Long userId, Long adoptionId, RequestStatus status) {
        switch (status) {
            case APPROVED:
                telegramBotSender.send(userId, String.format(MESSAGE_STATUS_APPROVED, adoptionId));
                break;
            case REJECTED:
                telegramBotSender.send(userId, MESSAGE_STATUS_REJECTED);
                break;
            case REJECTED_DURING_TRIAL_DATE:
                telegramBotSender.send(userId, MESSAGE_STATUS_REJECTED_DURING_TRIAL_DATE);
                break;
            default:
                break;
        }
    }
}
