package pro.dev.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.dev.animalshelter.model.Shelter;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
}
