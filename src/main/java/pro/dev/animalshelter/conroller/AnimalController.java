package pro.dev.animalshelter.conroller;

import org.springframework.web.bind.annotation.*;
import pro.dev.animalshelter.model.Animal;
import pro.dev.animalshelter.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    private final AnimalService service;

    public AnimalController(AnimalService service) {
        this.service = service;
    }

    @GetMapping
    public List<Animal> findAllAnimal() {
        return service.allAnimal();
    }

    @PostMapping("/add")
    public Animal addAnimal(@RequestParam Long idShelter, @RequestParam String nameAnimal, @RequestParam int ageAnimal) {
        return service.addAnimal(idShelter, nameAnimal, ageAnimal);
    }
    @DeleteMapping("{idRemoveAnimal}")
    public String removeAnimal(@PathVariable Long idAnimal) {
        return service.removeAnimal(idAnimal);
    }

    @DeleteMapping()
    public String clearAnimal() {
        return service.clearAnimal();
    }

    @GetMapping("{idAnimal}")
    public Animal findByIdAnimal(@PathVariable Long idAnimal) {
        return service.findByIdAnimal(idAnimal);
    }

}
