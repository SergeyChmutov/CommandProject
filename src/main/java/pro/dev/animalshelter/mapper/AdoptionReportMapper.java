package pro.dev.animalshelter.mapper;

import org.springframework.stereotype.Component;
import pro.dev.animalshelter.dto.AdoptionReportDTO;
import pro.dev.animalshelter.model.AdoptionReport;

@Component
public class AdoptionReportMapper {
    public AdoptionReportDTO mapToDTO(AdoptionReport report) {
        AdoptionReportDTO reportDTO = new AdoptionReportDTO(
                report.getPk().getAdoption().getId(),
                report.getPk().getReportDate(),
                report.getPk().getAdoption().getShelter().getId(),
                report.getPk().getAdoption().getAnimal().getIdAnimal(),
                report.getPk().getAdoption().getUser().getId(),
                report.getRation(),
                report.getWellBeing(),
                report.getBehaviourChange()
        );

        return reportDTO;
    }
}
