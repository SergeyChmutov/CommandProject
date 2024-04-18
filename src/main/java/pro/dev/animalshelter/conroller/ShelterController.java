package pro.dev.animalshelter.conroller;

import org.springframework.web.bind.annotation.*;
import pro.dev.animalshelter.interfaces.ShelterService;
import pro.dev.animalshelter.model.Animal;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.Users;

import java.util.List;

@RestController
@RequestMapping("/shelters")
public class ShelterController {
    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @PostMapping
    public Shelter addShelter(Shelter shelter) {
        return shelterService.addShelter(shelter);
    }

    @GetMapping
    public List<Shelter> getAllShelters() {
        return shelterService.getShelters();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        shelterService.deleteShelter(id);
    }

    @PutMapping("/{id}")
    public Shelter update(@PathVariable Long id, @RequestBody Shelter faculty) {
        return shelterService.updateShelter(id, faculty);
    }

    @GetMapping("{id}/volunteers")
    public List<Users> getVolunteers(@PathVariable Long id) {
        return shelterService.getVolunteers(id);
    }

    @GetMapping("{id}/animals")
    public List<Animal> getAnimals(@PathVariable Long id) {
        return shelterService.getAnimals(id);
    }
}
