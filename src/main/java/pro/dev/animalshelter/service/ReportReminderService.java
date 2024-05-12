package pro.dev.animalshelter.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.dev.animalshelter.enums.RequestStatus;
import pro.dev.animalshelter.interfaces.AdoptionReportInterface;
import pro.dev.animalshelter.interfaces.UsersId;

import java.time.LocalDate;
import java.util.Collection;

import static pro.dev.animalshelter.constant.Constants.*;

@Service
public class ReportReminderService {
    private final TelegramBotSender telegramBotSender;
    private final AdoptionReportInterface reportService;

    public ReportReminderService(TelegramBotSender telegramBotSender, AdoptionReportInterface reportService) {
        this.telegramBotSender = telegramBotSender;
        this.reportService = reportService;
    }

    @Scheduled(cron = "0 0 9 * * *")
    public void adoptionReportNotPresent() {
        LocalDate previousDayDate = LocalDate.now().minusDays(1L);
        Collection<UsersId> users = reportService.getUsersIdWhoDidNotReport(previousDayDate, RequestStatus.APPROVED);

        for (UsersId user : users) {
            telegramBotSender.send(user.getId(), MESSAGE_REPORT_NOT_FILED);
        }
    }

    @Scheduled(cron = "0 0 21 * * *")
    public void adoptionReportNotPresentOverTwoDays() {
        LocalDate previousDate = LocalDate.now().minusDays(2L);
    }
}
