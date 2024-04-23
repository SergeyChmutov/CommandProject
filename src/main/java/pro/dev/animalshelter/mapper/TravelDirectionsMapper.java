package pro.dev.animalshelter.mapper;

import org.springframework.stereotype.Component;
import pro.dev.animalshelter.dto.TravelDirectionsDTO;
import pro.dev.animalshelter.model.TravelDirections;

@Component
public class TravelDirectionsMapper {
    public TravelDirectionsDTO mapToDTO(TravelDirections travelDirections) {
        TravelDirectionsDTO travelDirectionsDTO = new TravelDirectionsDTO(
                travelDirections.getShelter().getId(),
                travelDirections.getFileSize(),
                travelDirections.getMediaType()
        );

        return travelDirectionsDTO;
    }
}
