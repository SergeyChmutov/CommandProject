package pro.dev.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.dev.animalshelter.model.ShelterInformation;
import pro.dev.animalshelter.model.ShelterInformationPK;

@Repository
public interface ShelterRepository extends JpaRepository<ShelterInformation, ShelterInformationPK> {
}
