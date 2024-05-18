package pro.dev.animalshelter.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.dev.animalshelter.enums.RequestStatus;
import pro.dev.animalshelter.interfaces.*;
import pro.dev.animalshelter.model.Adoption;
import pro.dev.animalshelter.model.Users;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static pro.dev.animalshelter.constant.Constants.MESSAGE_REPORT_NOT_FILED;
import static pro.dev.animalshelter.constant.Constants.MESSAGE_TRIAL_PERIOD_END;

@Service
public class ReportReminderService {
    private final TelegramBotSender telegramBotSender;
    private final AdoptionReportInterface reportService;
    private final ShelterService shelterService;
    private final AdoptionService adoptionService;

    public ReportReminderService(
            TelegramBotSender telegramBotSender,
            AdoptionReportInterface reportService,
            ShelterService shelterService,
            AdoptionService adoptionService
    ) {
        this.telegramBotSender = telegramBotSender;
        this.reportService = reportService;
        this.shelterService = shelterService;
        this.adoptionService = adoptionService;
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
        Collection<UsersWithoutReports> usersWithoutReports = reportService.getUsersWhoDidNotReportMoreThatTwoDays();

        if (usersWithoutReports.isEmpty()) {
            return;
        }

        Map<Long, List<UsersWithoutReports>> usersWithoutReportsGroupedByShelterId = usersWithoutReports.stream()
                .collect(Collectors.groupingBy(UsersWithoutReports::getShelterId));

        for (Long shelterId : usersWithoutReportsGroupedByShelterId.keySet()) {
            List<Users> shelterVolunteers = shelterService.getVolunteers(shelterId);

            if (shelterVolunteers.isEmpty()) {
                continue;
            }

            StringJoiner message = new StringJoiner("\n");
            message.add("Эти усыновители не присылали отчет уже более 2-х дней:");

            for (UsersWithoutReports user : usersWithoutReportsGroupedByShelterId.get(shelterId)) {
                message.add(user.getName() + ", тел. " + user.getPhone());
            }

            for (Users shelterVolunteer : shelterVolunteers) {
                telegramBotSender.send(shelterVolunteer.getId(), message.toString());
            }
        }
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void adoptionUserEndTrialPeriod() {
        LocalDate trialDate = LocalDate.now();
        Collection<Adoption> adoptionsByEndedTrialPeriod = adoptionService.getUsersWithEndedTrialDate(trialDate);

        for (Adoption adoption : adoptionsByEndedTrialPeriod) {
            telegramBotSender.send(adoption.getUser().getId(), MESSAGE_TRIAL_PERIOD_END);
        }
    }
}
