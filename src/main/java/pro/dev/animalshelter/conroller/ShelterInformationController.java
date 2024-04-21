package pro.dev.animalshelter.conroller;

import org.springframework.web.bind.annotation.*;
import pro.dev.animalshelter.dto.ShelterInformationDTO;
import pro.dev.animalshelter.enums.ShelterInformationProperty;
import pro.dev.animalshelter.mapper.ShelterInformationMapper;
import pro.dev.animalshelter.model.ShelterInformation;
import pro.dev.animalshelter.service.ShelterInformationService;

import java.util.Collection;

@RestController
@RequestMapping("/shelter-information")
public class ShelterInformationController {
    private final ShelterInformationService service;
    private final ShelterInformationMapper mapper;

    public ShelterInformationController(ShelterInformationService service, ShelterInformationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ShelterInformation saveInformationProperty(
            @RequestParam(name = "id") Long shelterId,
            @RequestParam(name = "property") String propertyName,
            @RequestParam(name = "value") String propertyValue
    ) {
        return service.saveShelterInformationProperty(shelterId, propertyName, propertyValue);
    }

    @GetMapping("/properties")
    public Collection<String> getPropertiesList() {
        return ShelterInformationProperty.getPropertiesList();
    }

    @GetMapping("/property")
    public ShelterInformationDTO getProperty(
            @RequestParam(name = "shelterId") Long id,
            @RequestParam(name = "property") String propertyName
    ) {
        ShelterInformation shelterInformation = service.getPropertyByShelterAndName(id, propertyName);
        return mapper.mapToDTO(shelterInformation);
    }
}
