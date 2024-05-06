package pro.dev.animalshelter.interfaces;

import pro.dev.animalshelter.enums.RequestStatus;
import pro.dev.animalshelter.model.Adoption;

import java.util.List;

public interface AdoptionService {
    Adoption addAdoption(Long animalId, Long userId, Long shelterId);

    Adoption getAdoption(Long id);

    List<Adoption> getAdoption();

    Adoption changeTrialPeriod(Long id, int daysToAdd);
    Adoption changeRequestStatus(Long id, RequestStatus status);

    void deleteAdoption(Long id);

}
