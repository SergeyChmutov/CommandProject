package pro.dev.animalshelter.conroller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.dev.animalshelter.dto.TravelDirectionsDTO;
import pro.dev.animalshelter.interfaces.TravelDirectionsInterface;
import pro.dev.animalshelter.mapper.TravelDirectionsMapper;
import pro.dev.animalshelter.model.TravelDirections;

import java.io.IOException;

@RestController
@RequestMapping("/travel-directions")
public class TravelDirectionsController {
    private final TravelDirectionsInterface travelDirectionsService;
    private final TravelDirectionsMapper travelDirectionsMapper;

    public TravelDirectionsController(TravelDirectionsInterface travelDirectionsService, TravelDirectionsMapper travelDirectionsMapper) {
        this.travelDirectionsService = travelDirectionsService;
        this.travelDirectionsMapper = travelDirectionsMapper;
    }

    @PostMapping(value = "/{shelterId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadTravelDirections(
            @PathVariable(name = "shelterId") Long shelterId,
            @RequestParam MultipartFile travelDirections
    ) throws IOException {
        travelDirectionsService.uploadTravelDirections(shelterId, travelDirections);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{shelterId}")
    public TravelDirectionsDTO getTravelDirectionsInfo(@PathVariable(name = "shelterId") Long id) {
        TravelDirections travelDirections = travelDirectionsService.getTravelDirectionsByShelterId(id);
        return travelDirectionsMapper.mapToDTO(travelDirections);
    }

    @GetMapping(value = "/{shelterId}/from-db")
    public ResponseEntity<byte[]> downloadTravelDirectionsFromDb(@PathVariable(name = "shelterId") Long id) {
        return travelDirectionsService.downloadTravelDirectionsFromDb(id);
    }
}
