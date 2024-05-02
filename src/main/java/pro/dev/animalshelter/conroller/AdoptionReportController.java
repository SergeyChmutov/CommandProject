package pro.dev.animalshelter.conroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.dev.animalshelter.dto.AdoptionReportDTO;
import pro.dev.animalshelter.interfaces.AdoptionReportInterface;
import pro.dev.animalshelter.mapper.AdoptionReportMapper;

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

    @GetMapping("/{adoptionId}")
    public Collection<AdoptionReportDTO> getReports(Long adoptionId) {
        return service.getAdoptionReportsByAdoptionId(adoptionId).stream()
                .map(mapper::mapToDTO)
                .toList();
    }
}
