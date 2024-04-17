package pro.dev.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.dev.animalshelter.model.ShelterInformation;
import pro.dev.animalshelter.model.ShelterInformationPK;

public interface ShelterInformationRepository extends JpaRepository<ShelterInformation, ShelterInformationPK> {
}
