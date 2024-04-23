package pro.dev.animalshelter.repository;

import pro.dev.animalshelter.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
