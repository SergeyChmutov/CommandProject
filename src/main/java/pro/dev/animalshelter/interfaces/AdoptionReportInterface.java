package pro.dev.animalshelter.interfaces;

import pro.dev.animalshelter.enums.RequestStatus;
import pro.dev.animalshelter.model.Adoption;
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

    AdoptionReport updateAdoptionReport(AdoptionReport report);

    AdoptionReport deleteAdoptionReport(Long adoptionId, LocalDate reportDate);

    Optional<AdoptionReport> getAdoptionReport(Long adoptionId, LocalDate reportDate);

    Collection<AdoptionReport> getAdoptionReportsByAdoptionId(Long adoptionId);

    void sendMessageBadReportFiled(Long userId);

    Collection<AdoptionReport> getAdoptionReportsByDate(LocalDate date);

    Collection<AdoptionReport> getNotWatchedAdoptionReports();

    AdoptionReport setAdoptionReportWatched(Long adoptionId, String reportDateText);

    Collection<UsersId> getUsersIdWhoDidNotReport(LocalDate reportDate, RequestStatus status);

    Collection<UsersWithoutReports> getUsersWhoDidNotReportMoreThatTwoDays();
}
