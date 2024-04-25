package pro.dev.animalshelter.conroller;

import org.springframework.web.bind.annotation.*;
import pro.dev.animalshelter.dto.RecommendationInformationDTO;
import pro.dev.animalshelter.interfaces.RecommendationInformationInterface;
import pro.dev.animalshelter.mapper.RecommendationInformationMapper;
import pro.dev.animalshelter.model.RecommendationInformation;

import java.util.Collection;

@RestController
@RequestMapping("/recommendations")
public class RecommendationInformationController {
    private final RecommendationInformationInterface service;
    private final RecommendationInformationMapper mapper;

    public RecommendationInformationController(
            RecommendationInformationInterface service,
            RecommendationInformationMapper mapper
    ) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/types")
    public Collection<String> getTypesList() {
        return service.getRecommendationTypesNameList();
    }

    @PostMapping
    public RecommendationInformation addRecommendation(
            @RequestParam(name = "type") String typeName,
            @RequestParam(name = "information") String information
    ) {
        return service.createRecommendationInformation(typeName, information);
    }

    @GetMapping
    public Collection<RecommendationInformationDTO> getAllRecommendations() {
        return service.getAllRecommendationInformation().stream()
                .map(mapper::mapToDTO)
                .toList();
    }

    @DeleteMapping("/{type}")
    public RecommendationInformation deleteRecommendation(@PathVariable(name = "type") String typeName) {
        return service.deleteRecommendationInformation(typeName);
    }

    @PutMapping()
    public RecommendationInformation updateRecommendation(
            @RequestParam(name = "type") String typeName,
            @RequestParam(name = "information") String information
    ) {
        return service.updateRecommendationInformation(typeName, information);
    }
}