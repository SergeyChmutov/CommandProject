package pro.dev.animalshelter.interfaces;

import pro.dev.animalshelter.enums.RequestStatus;
import pro.dev.animalshelter.model.Adoption;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AdoptionService {
    Adoption addAdoption(Long animalId, Long userId, Long shelterId);

    Adoption getAdoption(Long id);

    List<Adoption> getAdoption();

    Adoption changeTrialPeriod(Long id, int daysToAdd);

    Adoption changeRequestStatus(Long id, RequestStatus status);

    void deleteAdoption(Long id);

    Optional<Adoption> findAdoptionByUserIdAndStatus(Long userId, RequestStatus status);

    Optional<Adoption> findAdoptionByUserIdAndStatusAndTrialDate(Long userId, RequestStatus status, LocalDate trialDate);

    List<Adoption> getUsersWithEndedTrialDate(LocalDate trialDate);
}
