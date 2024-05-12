package pro.dev.animalshelter.service;

import org.springframework.stereotype.Service;
import pro.dev.animalshelter.enums.RequestStatus;
import pro.dev.animalshelter.exception.AdoptionReportNotFound;
import pro.dev.animalshelter.exception.AdoptionReportWrongReportDateException;
import pro.dev.animalshelter.interfaces.AdoptionReportInterface;
import pro.dev.animalshelter.interfaces.AdoptionService;
import pro.dev.animalshelter.interfaces.UsersId;
import pro.dev.animalshelter.model.Adoption;
import pro.dev.animalshelter.model.AdoptionReport;
import pro.dev.animalshelter.repository.AdoptionReportRepository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Optional;

import static pro.dev.animalshelter.constant.Constants.MESSAGE_REPORT_BAD_FILED;
import static pro.dev.animalshelter.constant.Constants.REPORT_DATE_FORMATTER;

@Service
public class AdoptionReportService implements AdoptionReportInterface {
    private final AdoptionReportRepository repository;
    private final AdoptionService adoptionService;
    private final TelegramBotSender telegramBotSender;

    public AdoptionReportService(
            AdoptionReportRepository repository,
            AdoptionService adoptionService,
            TelegramBotSender telegramBotSender
    ) {
        this.repository = repository;
        this.adoptionService = adoptionService;
        this.telegramBotSender = telegramBotSender;
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
    public AdoptionReport updateAdoptionReport(AdoptionReport report) {
        repository.save(report);
        return report;
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

    @Override
    public void sendMessageBadReportFiled(Long userId) {
        telegramBotSender.send(userId, MESSAGE_REPORT_BAD_FILED);
    }

    @Override
    public Collection<AdoptionReport> getAdoptionReportsByDate(LocalDate date) {
        return repository.findByPk_ReportDate(date);
    }

    @Override
    public Collection<AdoptionReport> getNotWatchedAdoptionReports() {
        return repository.findByWatchedFalse();
    }

    @Override
    public AdoptionReport setAdoptionReportWatched(Long adoptionId, String reportDateText) {
        LocalDate reportDate;
        try {
            reportDate = LocalDate.parse(reportDateText, REPORT_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new AdoptionReportWrongReportDateException("Ошибка в указании даты отчета: " + reportDateText);
        }

        Optional<AdoptionReport> savedReport = getAdoptionReport(adoptionId, reportDate);

        if (savedReport.isPresent()) {
            AdoptionReport report = savedReport.get();
            report.setWatched(true);
            repository.save(report);
            return report;
        } else {
            throw new AdoptionReportNotFound(adoptionId, reportDate);
        }
    }

    @Override
    public Collection<UsersId> getUsersIdWhoDidNotReport(LocalDate reportDate, RequestStatus status) {
        return repository.findUsersIdWhoDidNotReport(reportDate, status);
    }
}
