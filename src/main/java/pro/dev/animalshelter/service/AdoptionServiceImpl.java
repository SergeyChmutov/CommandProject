package pro.dev.animalshelter.service;

import org.springframework.stereotype.Service;
import pro.dev.animalshelter.dto.AdoptionDTO;
import pro.dev.animalshelter.enums.RequestStatus;
import pro.dev.animalshelter.exception.AdoptionNotFoundException;
import pro.dev.animalshelter.interfaces.AdoptionService;
import pro.dev.animalshelter.mapper.AdoptionMapper;
import pro.dev.animalshelter.model.Adoption;
import pro.dev.animalshelter.model.Animal;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.Users;
import pro.dev.animalshelter.repository.AdoptionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdoptionServiceImpl implements AdoptionService {

    private final AdoptionRepository adoptionRepository;

    public AdoptionServiceImpl(AdoptionRepository adoptionRepository) {
        this.adoptionRepository = adoptionRepository;
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
                .orElseThrow(() -> new AdoptionNotFoundException("Запись не найдена"));

        if (adoption.getTrialDate() != null) {
            LocalDate currentTrialPeriodEndDate = adoption.getTrialDate();
            adoption.setTrialDate(getNewTrialPeriod(currentTrialPeriodEndDate, daysToAdd));
        } else {
            LocalDate currentTrialPeriodEndDate = LocalDate.now();
            adoption.setTrialDate(getNewTrialPeriod(currentTrialPeriodEndDate, daysToAdd));
        }
        return adoptionRepository.save(adoption);
    }

    @Override
    public Adoption changeRequestStatus(Long id, RequestStatus status) {
        return adoptionRepository.findById(id).map(adoption -> {
            adoption.setStatus(status);
            return adoptionRepository.save(adoption);
        }).orElseThrow(() -> new AdoptionNotFoundException("Запись не найдена"));
    }

    @Override
    public void deleteAdoption(Long id) {
        adoptionRepository.deleteById(id);
    }

    private static LocalDate getNewTrialPeriod(LocalDate currentTrialPeriodEndDate, int daysToAdd) {
        return currentTrialPeriodEndDate.plusDays(daysToAdd);
    }
}
