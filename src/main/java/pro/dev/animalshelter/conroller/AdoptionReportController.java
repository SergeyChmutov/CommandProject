package pro.dev.animalshelter.conroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.dev.animalshelter.dto.AdoptionReportDTO;
import pro.dev.animalshelter.interfaces.AdoptionReportAnimalPhotoInterface;
import pro.dev.animalshelter.interfaces.AdoptionReportInterface;
import pro.dev.animalshelter.mapper.AdoptionReportMapper;
import pro.dev.animalshelter.model.AdoptionReportAnimalPhoto;

import java.util.Collection;

@RestController
@RequestMapping("/adoption-report")
public class AdoptionReportController {
    private final AdoptionReportInterface service;
    private final AdoptionReportAnimalPhotoInterface photoService;
    private final AdoptionReportMapper mapper;

    public AdoptionReportController(
            AdoptionReportInterface service,
            AdoptionReportAnimalPhotoInterface photoService,
            AdoptionReportMapper mapper
    ) {
        this.service = service;
        this.photoService = photoService;
        this.mapper = mapper;
    }

    @GetMapping("/{adoptionId}")
    public Collection<AdoptionReportDTO> getReports(@PathVariable(name = "adoptionId") Long id) {
        return service.getAdoptionReportsByAdoptionId(id).stream()
                .map(mapper::mapToDTO)
                .toList();
    }

    @GetMapping("/photo/")
    public ResponseEntity<byte[]> downloadAnimalPhotoReportFromDb(
            @RequestParam(name = "adoptionId") Long id,
            @RequestParam(name = "reportDate") String reportDateString
            ) {
        return photoService.downloadAnimalPhotoReportFromDb(id, reportDateString);
    }
}
