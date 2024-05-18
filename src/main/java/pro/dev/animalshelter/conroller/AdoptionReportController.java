package pro.dev.animalshelter.conroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.dev.animalshelter.dto.AdoptionReportDTO;
import pro.dev.animalshelter.interfaces.AdoptionReportAnimalPhotoInterface;
import pro.dev.animalshelter.interfaces.AdoptionReportInterface;
import pro.dev.animalshelter.mapper.AdoptionReportMapper;

import java.time.LocalDate;
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

    @GetMapping("/now")
    public ResponseEntity<Collection<AdoptionReportDTO>> getReportsByDay() {
        return ResponseEntity.ok(service.getAdoptionReportsByDate(LocalDate.now()).stream()
                .map(mapper::mapToDTO)
                .toList());
    }

    @GetMapping("/not-watched")
    public ResponseEntity<Collection<AdoptionReportDTO>> getNotWatchedReports() {
        return ResponseEntity.ok(service.getNotWatchedAdoptionReports().stream()
                .map(mapper::mapToDTO)
                .toList());
    }

    @GetMapping("/{adoptionId}")
    public ResponseEntity<Collection<AdoptionReportDTO>> getReports(@PathVariable(name = "adoptionId") Long id) {
        return ResponseEntity.ok(service.getAdoptionReportsByAdoptionId(id).stream()
                .map(mapper::mapToDTO)
                .toList());
    }

    @GetMapping("/photo/")
    public ResponseEntity<byte[]> downloadAnimalPhotoReportFromDb(
            @RequestParam(name = "adoptionId") Long id,
            @RequestParam(name = "reportDate") String reportDateString
    ) {
        return photoService.downloadAnimalPhotoReportFromDb(id, reportDateString);
    }

    @PutMapping("/send-bad-filed")
    public ResponseEntity<String> sendMessageAboutBadReportFiled(@RequestParam(name = "userId") Long id) {
        service.sendMessageBadReportFiled(id);
        return ResponseEntity.ok("Сообщение отправлено.");
    }

    @PutMapping("/watched")
    public ResponseEntity<String> setReportWatched(
            @RequestParam(name = "adoptionId") Long id,
            @RequestParam(name = "reportDate") String reportDateString
    ) {
        service.setAdoptionReportWatched(id, reportDateString);
        return ResponseEntity.ok("Отчет просмотрен");
    }
}
