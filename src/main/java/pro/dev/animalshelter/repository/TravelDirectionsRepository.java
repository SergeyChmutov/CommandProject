package pro.dev.animalshelter.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.dev.animalshelter.model.TravelDirections;

import java.util.Optional;

@Repository
@Transactional
public interface TravelDirectionsRepository extends JpaRepository<TravelDirections, Long> {
    Optional<TravelDirections> findByShelterId(long id);
}
