package pro.dev.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.dev.animalshelter.enums.RequestStatus;
import pro.dev.animalshelter.interfaces.UsersId;
import pro.dev.animalshelter.interfaces.UsersWithoutReports;
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
            SELECT DISTINCT
            	adoption.user_id id
            FROM
            	(SELECT
            	 	adoption.id,
            	 	adoption.user_id
            	 FROM
            	    adoption
            	 WHERE adoption.request_status = ?2 AND adoption.trial_date >= ?1) adoption
            LEFT JOIN adoption_report ON adoption_report.adoption_id = adoption.id AND adoption_report.report_date = ?1
            WHERE adoption_report.adoption_id IS NULL
            """, nativeQuery = true)
    Collection<UsersId> findUsersIdWhoDidNotReport(LocalDate reportDate, RequestStatus status);

    @Query(value = """
            SELECT DISTINCT
            	adoption.shelter_id,
            	users.name,
            	users.phone
            FROM
            	(SELECT
            		adoptions.id,
            		count(adoption_report.report_date)
            	FROM
            		(SELECT
            			adoption.id
            		FROM
            			adoption
            		WHERE
            			adoption.request_status = 'APPROVED' AND adoption.confirmation_date <= '2024-05-13' AND adoption.trial_date >= '2024-05-15') adoptions
            		LEFT JOIN adoption_report ON adoptions.id = adoption_report.adoption_id AND adoption_report.report_date >= '2024-05-13'
            	GROUP BY adoptions.id
            	HAVING count(adoption_report.report_date) = 0) adoptions
            INNER JOIN adoption ON adoption.id = adoptions.id
            INNER JOIN users ON adoption.user_id = users.id
            """, nativeQuery = true)
    Collection<UsersWithoutReports> findUsersWhoDidNotReportMoreThatTwoDays(
            LocalDate DateTwoDayAgo,
            LocalDate reportDate,
            RequestStatus status
    );
}