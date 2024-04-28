package pro.dev.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.dev.animalshelter.enums.RecommendationType;
import pro.dev.animalshelter.model.RecommendationInformation;

import java.util.Optional;

@Repository
public interface RecommendationInformationRepository extends JpaRepository<RecommendationInformation, Long> {
    Optional<RecommendationInformation> findByType(RecommendationType type);

    boolean existsByType(RecommendationType type);
}
