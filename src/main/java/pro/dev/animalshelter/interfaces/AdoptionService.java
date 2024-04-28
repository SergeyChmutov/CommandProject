package pro.dev.animalshelter.interfaces;

import pro.dev.animalshelter.model.Adoption;

import java.util.List;

public interface AdoptionService {
    Adoption addAdoption(Long animalId, Long userId, Long shelterId);

    Adoption getAdoption(Long id);

    List<Adoption> getAdoption();

    Adoption prolongTrialPeriod(Long id, int daysToAdd);

    void deleteAdoption(Long id);

}
