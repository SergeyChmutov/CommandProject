package pro.dev.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.dev.animalshelter.model.AdoptionReport;
import pro.dev.animalshelter.model.AdoptionReportPK;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface AdoptionReportRepository extends JpaRepository<AdoptionReport, AdoptionReportPK> {
    Optional<AdoptionReport> findByPk_Adoption_IdAndPk_ReportDate(Long id, LocalDate reportDate);

    Collection<AdoptionReport> findByPk_Adoption_IdOrderByPk_ReportDateDesc(Long id);
}