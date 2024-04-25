package pro.dev.animalshelter.mapper;

import org.springframework.stereotype.Component;
import pro.dev.animalshelter.dto.RecommendationInformationDTO;
import pro.dev.animalshelter.model.RecommendationInformation;

@Component
public class RecommendationInformationMapper {
    public RecommendationInformationDTO mapToDTO(RecommendationInformation information) {
        return new RecommendationInformationDTO(information.getType(), information.getInformation());
    }
}
