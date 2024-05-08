package pro.dev.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.dev.animalshelter.model.Animal;
import pro.dev.animalshelter.model.Shelter;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
}
