package pro.dev.animalshelter.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import pro.dev.animalshelter.model.TravelDirections;

import java.io.IOException;

public interface TravelDirectionsInterface {
    TravelDirections getTravelDirectionsByShelterId(Long id);

    void uploadTravelDirections(Long id, MultipartFile travelDirectionsFile) throws IOException;

    ResponseEntity<byte[]> downloadTravelDirectionsFromDb(Long id);

    byte[] downloadTravelDirectionsDataFromDb(Long id);
}
