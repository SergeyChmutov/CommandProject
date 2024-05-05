package pro.dev.animalshelter.mapper;

import org.springframework.stereotype.Component;
import pro.dev.animalshelter.dto.AdoptionDTO;
import pro.dev.animalshelter.dto.ShelterInformationDTO;
import pro.dev.animalshelter.model.Adoption;
import pro.dev.animalshelter.model.ShelterInformation;

@Component
public class AdoptionMapper {
    public AdoptionDTO mapToDTO(Adoption adoption) {
        return new AdoptionDTO(
                adoption.getUser().getId(),
                adoption.getAnimal().getIdAnimal(),
                adoption.getShelter().getId(),
                adoption.getStatus(),
                adoption.getConfirmationDate(),
                adoption.getTrialDate()
        );
    }
}
