package pro.dev.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.dev.animalshelter.enums.RequestStatus;
import pro.dev.animalshelter.interfaces.UsersId;
import pro.dev.animalshelter.model.AdoptionReport;
import pro.dev.animalshelter.model.AdoptionReportPK;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface AdoptionReportRepository extends JpaRepository<AdoptionReport, AdoptionReportPK> {
    Optional<AdoptionReport> findByPk_Adoption_IdAndPk_ReportDate(Long id, LocalDate reportDate);

    Collection<AdoptionReport> findByPk_Adoption_IdOrderByPk_ReportDateDesc(Long id);

    Collection<AdoptionReport> findByPk_ReportDate(LocalDate reportDate);

    Collection<AdoptionReport> findByWatchedFalse();

    @Query(value = """
            SELECT adoption.user_id id
            FROM adoption
            LEFT JOIN adoption_report ar ON ar.adoption_id = adoption.id AND ar.report_date = ?1
            WHERE adoption.request_status = ?2 AND adoption.trial_date >= ?1 AND ar.adoption_id IS NULL
            """, nativeQuery = true)
    Collection<UsersId> findUsersIdWhoDidNotReport(LocalDate reportDate, RequestStatus status);
}