package pro.dev.animalshelter.conroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.service.ShelterService;

@RestController
@RequestMapping("/shelter")
public class ShelterController {
    private final ShelterService service;

    public ShelterController(ShelterService service) {
        this.service = service;
    }

    @PostMapping
    public Shelter saveInformationProperty(@RequestParam String name) {
        return service.createShelter(name);
    }
}