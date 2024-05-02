package pro.dev.animalshelter.conroller;

import org.springframework.web.bind.annotation.*;
import pro.dev.animalshelter.dto.AdoptionReportDTO;
import pro.dev.animalshelter.interfaces.AdoptionReportInterface;
import pro.dev.animalshelter.mapper.AdoptionReportMapper;
import pro.dev.animalshelter.model.AdoptionReport;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/adoption-report")
public class AdoptionReportController {
    private final AdoptionReportInterface service;
    private final AdoptionReportMapper mapper;

    public AdoptionReportController(AdoptionReportInterface service, AdoptionReportMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public AdoptionReportDTO addReport(@RequestParam(name = "adoptionId") Long adoptionId,
                                       @RequestParam(name = "date") LocalDate reportDate,
                                       @RequestParam(name = "ration") String ration,
                                       @RequestParam(name = "well-being") String wellBeing,
                                       @RequestParam(name = "behavior") String behaviourChange

    ) {
        AdoptionReport createdReport = service.createAdoptionReport(
                adoptionId,
                reportDate,
                ration,
                wellBeing,
                behaviourChange
        );

        return mapper.mapToDTO(createdReport);
    }

    @GetMapping("/{adoptionId}")
    public Collection<AdoptionReportDTO> getReports(Long id) {
        return service.getAdoptionReportsByAdoptionId(id).stream()
                .map(mapper::mapToDTO)
                .toList();
    }

    @DeleteMapping
    public AdoptionReportDTO deleteReport(@RequestParam(name = "adoptionId") Long adoptionId,
                                          @RequestParam(name = "date") LocalDate reportDate) {
        return mapper.mapToDTO(service.deleteAdoptionReport(adoptionId, reportDate));
    }

    @PutMapping
    public AdoptionReportDTO updateReport(@RequestParam(name = "adoptionId") Long adoptionId,
                                          @RequestParam(name = "date") LocalDate reportDate,
                                          @RequestParam(name = "ration") String ration,
                                          @RequestParam(name = "well-being") String wellBeing,
                                          @RequestParam(name = "behavior") String behaviourChange
    ) {
        AdoptionReport updatedReport = service.updateAdoptionReport(
                adoptionId,
                reportDate,
                ration,
                wellBeing,
                behaviourChange
        );

        return mapper.mapToDTO(updatedReport);
    }
}
