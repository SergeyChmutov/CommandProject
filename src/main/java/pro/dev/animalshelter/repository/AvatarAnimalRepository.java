package pro.dev.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.dev.animalshelter.model.AvatarAnimal;
import java.util.Optional;
public interface AvatarAnimalRepository extends JpaRepository<AvatarAnimal, Long> {
    Optional<AvatarAnimal> findById(Long idAnimal);
}
