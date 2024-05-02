package pro.dev.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.dev.animalshelter.model.AdoptionReport;
import pro.dev.animalshelter.model.AdoptionReportAnimalPhoto;
import pro.dev.animalshelter.model.AdoptionReportPK;

import java.util.Optional;

@Repository
public interface AdoptionReportAnimalPhotoRepository extends JpaRepository<AdoptionReportAnimalPhoto, Long> {
    Optional<AdoptionReportAnimalPhoto> findByReport(AdoptionReport report);

    Optional<AdoptionReportAnimalPhoto> findByReport_Pk(AdoptionReportPK pk);
}
