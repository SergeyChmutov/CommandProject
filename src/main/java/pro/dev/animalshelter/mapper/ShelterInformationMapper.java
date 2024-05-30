package pro.dev.animalshelter.mapper;

import org.springframework.stereotype.Component;
import pro.dev.animalshelter.dto.ShelterInformationDTO;
import pro.dev.animalshelter.model.ShelterInformation;

@Component
public class ShelterInformationMapper {
    public ShelterInformationDTO mapToDTO(ShelterInformation information) {
        return new ShelterInformationDTO(
                information.getPk().getShelter().getId(),
                information.getPk().getShelterInformationProperty().getPropertyName(),
                information.getDescription()
        );
    }
}
