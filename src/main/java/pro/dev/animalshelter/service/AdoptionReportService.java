package pro.dev.animalshelter.service;

import org.springframework.stereotype.Service;
import pro.dev.animalshelter.exception.AdoptionReportNotFound;
import pro.dev.animalshelter.interfaces.AdoptionReportInterface;
import pro.dev.animalshelter.interfaces.AdoptionService;
import pro.dev.animalshelter.model.Adoption;
import pro.dev.animalshelter.model.AdoptionReport;
import pro.dev.animalshelter.repository.AdoptionReportRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
public class AdoptionReportService implements AdoptionReportInterface {
    private final AdoptionReportRepository repository;
    private final AdoptionService adoptionService;

    public AdoptionReportService(AdoptionReportRepository repository, AdoptionService adoptionService) {
        this.repository = repository;
        this.adoptionService = adoptionService;
    }

    @Override
    public AdoptionReport createAdoptionReport(
            Long adoptionId,
            LocalDate reportDate,
            String ration,
            String wellBeing,
            String behaviourChange
    ) {
        Adoption adoption = adoptionService.getAdoption(adoptionId);
        AdoptionReport report = new AdoptionReport(adoption, reportDate, ration, wellBeing, behaviourChange);
        repository.save(report);
        return report;
    }

    @Override
    public AdoptionReport updateAdoptionReport(
            Long adoptionId,
            LocalDate reportDate,
            String ration,
            String wellBeing,
            String behaviourChange
    ) {
        Optional<AdoptionReport> savedReport = repository.findByPk_Adoption_IdAndPk_ReportDate(adoptionId, reportDate);

        if (!savedReport.isPresent()) {
            throw new AdoptionReportNotFound(
                    "Не найден отчет по идентификатору усыновления " + adoptionId + " и дате отчета " + reportDate
            );
        } else {
            AdoptionReport report = savedReport.get();
            report.setRation(ration);
            report.setWellBeing(wellBeing);
            report.setBehaviourChange(behaviourChange);
            repository.save(report);
            return report;
        }
    }

    @Override
    public AdoptionReport deleteAdoptionReport(Long adoptionId, LocalDate reportDate) {
        Optional<AdoptionReport> savedReport = repository.findByPk_Adoption_IdAndPk_ReportDate(adoptionId, reportDate);

        if (!savedReport.isPresent()) {
            throw new AdoptionReportNotFound(
                    "Не найден отчет по идентификатору усыновления " + adoptionId + " и дате отчета " + reportDate
            );
        } else {
            AdoptionReport report = savedReport.get();
            repository.delete(report);
            return report;
        }
    }

    @Override
    public Collection<AdoptionReport> getAdoptionReportsByAdoptionId(Long adoptionId) {
        return repository.findByPk_Adoption_IdOrderByPk_ReportDateDesc(adoptionId);
    }

    @Override
    public Optional<AdoptionReport> getAdoptionReport(Long adoptionId, LocalDate reportDate) {
        return repository.findByPk_Adoption_IdAndPk_ReportDate(adoptionId, reportDate);
    }
}
