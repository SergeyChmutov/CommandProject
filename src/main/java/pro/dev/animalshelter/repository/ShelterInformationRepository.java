package pro.dev.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.dev.animalshelter.enums.ShelterInformationProperty;
import pro.dev.animalshelter.model.ShelterInformation;
import pro.dev.animalshelter.model.ShelterInformationPK;

import java.util.Optional;

public interface ShelterInformationRepository extends JpaRepository<ShelterInformation, ShelterInformationPK> {
    Optional<ShelterInformation> findByPkShelterIdAndPkShelterInformationProperty(
            long id,
            ShelterInformationProperty shelterInformationProperty
    );
}
