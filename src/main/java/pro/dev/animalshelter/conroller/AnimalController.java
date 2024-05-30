package pro.dev.animalshelter.conroller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.dev.animalshelter.interfaces.AvatarAnimalService;
import pro.dev.animalshelter.model.Animal;
import pro.dev.animalshelter.service.AnimalService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    private final AnimalService service;

    private final AvatarAnimalService avatarAnimalService;

    public AnimalController(AnimalService service, AvatarAnimalService avatarAnimalService) {
        this.service = service;
        this.avatarAnimalService = avatarAnimalService;
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

    @PostMapping(value = "/{animalId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long animalId, @RequestParam MultipartFile avatar) throws IOException {
        avatarAnimalService.uploadAvatarAnimal(animalId, avatar);
        return ResponseEntity.ok().build();
    }
}
