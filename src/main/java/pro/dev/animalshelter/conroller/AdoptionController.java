package pro.dev.animalshelter.conroller;

import org.springframework.web.bind.annotation.*;
import pro.dev.animalshelter.interfaces.AdoptionService;
import pro.dev.animalshelter.model.*;

import java.util.List;

@RestController
@RequestMapping("/adoption")
public class AdoptionController {
    private final AdoptionService adoptionService;

    public AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @PostMapping
    public Adoption addAdoption(@RequestParam(name = "animalId") Long animalId,
                                @RequestParam(name = "userId") Long userId,
                                @RequestParam(name = "shelterId") Long shelterId) {

        return adoptionService.addAdoption(animalId, userId, shelterId);
    }
    @GetMapping("/{id}")
    public Adoption getAdoptions(Long id) {
        return adoptionService.getAdoption(id);
    }
    @GetMapping
    public List<Adoption> getAllAdoptions() {
        return adoptionService.getAdoption();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        adoptionService.deleteAdoption(id);
    }

    @PutMapping("/{id}")
    public Adoption prolongTrialPeriod(@RequestParam(name = "adoptionId") Long adoptionId,
                                       @RequestParam(name = "daysToAdd") int daysToAdd) {
        return adoptionService.prolongTrialPeriod(adoptionId, daysToAdd);
    }

}
