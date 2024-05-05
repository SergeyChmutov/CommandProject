package pro.dev.animalshelter.exception;

import java.time.LocalDate;

public class AdoptionReportNotFound extends RuntimeException {
    public AdoptionReportNotFound(String message) {
        super(message);
    }

    public AdoptionReportNotFound(Long adoptionId, LocalDate reportDate) {
        super("Не найден отчет по идентификатору усыновления " + adoptionId + " и дате отчета " + reportDate);
    }

}