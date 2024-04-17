package pro.dev.animalshelter.conroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.dev.animalshelter.model.ShelterInformation;
import pro.dev.animalshelter.service.ShelterInformationService;

@RestController
@RequestMapping("/shelter-information")
public class ShelterInformationController {
    private final ShelterInformationService service;

    public ShelterInformationController(ShelterInformationService service) {
        this.service = service;
    }

    @PostMapping
    public ShelterInformation saveInformationProperty(
            @RequestParam(name = "id") Long shelterId,
            @RequestParam(name = "property") String propertyName,
            @RequestParam(name = "value") String propertyValue
    ) {
        return service.saveShelterInformationProperty(shelterId, propertyName, propertyValue);
    }

}
