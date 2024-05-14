package pro.dev.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.dev.animalshelter.enums.RequestStatus;
import pro.dev.animalshelter.model.Adoption;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    Optional<Adoption> findByUser_IdAndStatus(Long id, RequestStatus status);

    Optional<Adoption> findByUser_IdAndStatusAndTrialDateLessThanEqual(
            Long userId,
            RequestStatus status,
            LocalDate trialDate
    );

    List<Adoption> findByStatusAndTrialDate(RequestStatus status, LocalDate trialDate);
}
