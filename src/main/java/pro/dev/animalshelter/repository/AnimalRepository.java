package pro.dev.animalshelter.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.dev.animalshelter.model.Animal;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByShelter_Id(Long id, Pageable pageable);

    Long countByShelter_Id(Long id);
}