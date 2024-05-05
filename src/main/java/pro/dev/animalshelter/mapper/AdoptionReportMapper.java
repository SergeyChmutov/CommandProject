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
                report.getPk().getAdoption().getAdoptionRequest().getShelter().getId(),
                report.getPk().getAdoption().getAdoptionRequest().getAnimal().getIdAnimal(),
                report.getPk().getAdoption().getAdoptionRequest().getUser().getId(),
                report.getRation(),
                report.getWellBeing(),
                report.getBehaviourChange()
        );

        return reportDTO;
    }
}
