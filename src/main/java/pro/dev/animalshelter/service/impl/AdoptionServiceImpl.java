package pro.dev.animalshelter.service.impl;

import org.springframework.stereotype.Service;
import pro.dev.animalshelter.exception.AdoptionNotFoundException;
import pro.dev.animalshelter.interfaces.AdoptionService;
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

        LocalDate confirmationDate = LocalDate.now();
        LocalDate trialDate = LocalDate.now().plusDays(30);

        Adoption adoption = new Adoption(shelter, user, animal, confirmationDate, trialDate);
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
    public Adoption prolongTrialPeriod(Long id, int daysToAdd) {
        return adoptionRepository.findById(id).map(adoption -> {
            LocalDate currentTrialPeriodEndDate = adoption.getTrialDate();
            LocalDate newTrialPeriodEndDate = currentTrialPeriodEndDate.plusDays(daysToAdd);
            adoption.setTrialDate(newTrialPeriodEndDate);
            return adoptionRepository.save(adoption);
        }).orElseThrow(() -> new AdoptionNotFoundException("Запись не найдена"));
    }

    @Override
    public void deleteAdoption(Long id) {
        adoptionRepository.deleteById(id);
    }
}
