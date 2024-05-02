package pro.dev.animalshelter.interfaces;

import pro.dev.animalshelter.model.AdoptionReport;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface AdoptionReportInterface {
    AdoptionReport createAdoptionReport(
            Long adoptionId,
            LocalDate reportDate,
            String ration,
            String wellBeing,
            String behaviourChange
    );

    AdoptionReport updateAdoptionReport(
            Long adoptionId,
            LocalDate reportDate,
            String ration,
            String wellBeing,
            String behaviourChange
    );

    AdoptionReport deleteAdoptionReport(Long adoptionId, LocalDate reportDate);

    Optional<AdoptionReport> getAdoptionReport(Long adoptionId, LocalDate reportDate);

    Collection<AdoptionReport> getAdoptionReportsByAdoptionId(Long adoptionId);
}
