package pro.dev.animalshelter.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.dev.animalshelter.exception.TravelDirectionsNotFoundException;
import pro.dev.animalshelter.interfaces.ShelterService;
import pro.dev.animalshelter.interfaces.TravelDirectionsInterface;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.TravelDirections;
import pro.dev.animalshelter.repository.TravelDirectionsRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class TravelDirectionsService implements TravelDirectionsInterface {
    private final TravelDirectionsRepository repository;
    private final ShelterService shelterService;

    public TravelDirectionsService(TravelDirectionsRepository repository, ShelterService shelterService) {
        this.repository = repository;
        this.shelterService = shelterService;
    }

    @Override
    public TravelDirections getTravelDirectionsByShelterId(Long id) {
        return repository.findByShelterId(id).orElseThrow(
                () -> new TravelDirectionsNotFoundException("Не найдена схема проезда для приюта с идентификатором " + id)
        );
    }

    @Override
    public void uploadTravelDirections(Long id, MultipartFile travelDirectionsFile) throws IOException {
        final Shelter shelter = shelterService.getShelter(id);

        final TravelDirections travelDirections = findTravelDirectoriesByShelterIdOrCreateWhenNotFound(id);
        travelDirections.setShelter(shelter);
        travelDirections.setFileSize(travelDirectionsFile.getSize());
        travelDirections.setMediaType(travelDirectionsFile.getContentType());
        travelDirections.setData(travelDirectionsFile.getBytes());
        repository.save(travelDirections);
    }

    @Override
    public ResponseEntity<byte[]> downloadTravelDirectionsFromDb(Long id) {
        TravelDirections travelDirections = getTravelDirectionsByShelterId(id);
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType(travelDirections.getMediaType()));
        headers.setContentLength(travelDirections.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(travelDirections.getData());
    }

    @Override
    public byte[] downloadTravelDirectionsDataFromDb(Long id) {
        Optional<TravelDirections> travelDirections = repository.findByShelterId(id);
        if (travelDirections.isPresent()) {
            return travelDirections.get().getData();
        } else {
            throw new TravelDirectionsNotFoundException("Информация по схеме проезда для приюта не указана");
        }
    }

    private TravelDirections findTravelDirectoriesByShelterIdOrCreateWhenNotFound(Long id) {
        return repository.findByShelterId(id).orElse(new TravelDirections());
    }
}
