package pro.dev.animalshelter.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.dev.animalshelter.interfaces.ShelterService;
import pro.dev.animalshelter.interfaces.TravelDirectionsInterface;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.TravelDirections;
import pro.dev.animalshelter.repository.TravelDirectionsRepository;

import java.io.IOException;

@Service
public class TravelDirectionsService implements TravelDirectionsInterface {
    private final TravelDirectionsRepository repository;
    private final ShelterService shelterService;

    public TravelDirectionsService(TravelDirectionsRepository repository, ShelterService shelterService) {
        this.repository = repository;
        this.shelterService = shelterService;
    }

    public TravelDirections getTravelDirectionsByShelterId(Long id) {
        return repository.findByShelterId(id).orElseThrow(RuntimeException::new);
    }

    public void uploadTravelDirections(Long id, MultipartFile travelDirectionsFile) throws IOException {
        final Shelter shelter = shelterService.getShelter(id);

        final TravelDirections travelDirections = findTravelDirectoriesByShelterIdOrCreateWhenNotFound(id);
        travelDirections.setShelter(shelter);
        travelDirections.setFileSize(travelDirectionsFile.getSize());
        travelDirections.setMediaType(travelDirectionsFile.getContentType());
        travelDirections.setData(travelDirectionsFile.getBytes());
        repository.save(travelDirections);
    }

    public ResponseEntity<byte[]> downloadTravelDirectionsFromDb(Long id) {
        TravelDirections travelDirections = getTravelDirectionsByShelterId(id);
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType(travelDirections.getMediaType()));
        headers.setContentLength(travelDirections.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(travelDirections.getData());
    }

    public ResponseEntity<byte[]> downloadFromDb(Long id) {
        TravelDirections travelDirections = getTravelDirectionsByShelterId(id);
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType(travelDirections.getMediaType()));
        headers.setContentLength(travelDirections.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(travelDirections.getData());
    }

    private TravelDirections findTravelDirectoriesByShelterIdOrCreateWhenNotFound(Long id) {
        return repository.findByShelterId(id).orElse(new TravelDirections());
    }
}
