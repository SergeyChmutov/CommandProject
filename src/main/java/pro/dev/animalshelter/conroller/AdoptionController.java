package pro.dev.animalshelter.conroller;

import org.springframework.web.bind.annotation.*;
import pro.dev.animalshelter.dto.AdoptionDTO;
import pro.dev.animalshelter.enums.RequestStatus;
import pro.dev.animalshelter.interfaces.AdoptionService;
import pro.dev.animalshelter.mapper.AdoptionMapper;
import pro.dev.animalshelter.model.*;

import java.util.List;

@RestController
@RequestMapping("/adoption")
public class AdoptionController {
    private final AdoptionService adoptionService;

    private final AdoptionMapper adoptionMapper;
    public AdoptionController(AdoptionService adoptionService, AdoptionMapper adoptionMapper) {
        this.adoptionService = adoptionService;
        this.adoptionMapper = adoptionMapper;
    }

    @PostMapping
    public AdoptionDTO addAdoption(@RequestParam(name = "animalId") Long animalId,
                                   @RequestParam(name = "userId") Long userId,
                                   @RequestParam(name = "shelterId") Long shelterId) {

        return adoptionMapper.mapToDTO(adoptionService.addAdoption(animalId, userId, shelterId));
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

    @PutMapping("/{id}/trialPeriod")
    public Adoption changeTrialPeriod(@RequestParam(name = "adoptionId") Long adoptionId,
                                       @RequestParam(name = "daysToAdd") int daysToAdd) {
        return adoptionService.changeTrialPeriod(adoptionId, daysToAdd);
    }

    @PutMapping("/{id}/requestStatus")
    public Adoption changeRequestStatus(@RequestParam(name = "adoptionId") Long adoptionId,
                                        @RequestParam(name = "daysToAdd")RequestStatus status) {
        return adoptionService.changeRequestStatus(adoptionId, status);
    }
}
