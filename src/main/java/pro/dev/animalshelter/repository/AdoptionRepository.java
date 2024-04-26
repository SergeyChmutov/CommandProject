package pro.dev.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.dev.animalshelter.model.Adoption;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
}
