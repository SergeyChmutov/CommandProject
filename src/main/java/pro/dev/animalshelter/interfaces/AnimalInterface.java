package pro.dev.animalshelter.interfaces;

import org.springframework.data.domain.Pageable;
import pro.dev.animalshelter.model.Animal;

import java.util.Collection;
import java.util.List;

public interface AnimalInterface {
    Animal addAnimal (Long idAnimal, String nameAnimal, int ageAnimal);

    List<Animal> allAnimal();

    String removeAnimal(Long idAnimal);

    String clearAnimal();

    Animal findByIdAnimal(Long idAnimal);

    Boolean existsByIdAnimal(Long idAnimal);

    Long animalCountByShelterId(Long id);

    List<Animal> getPaginatedAnimalByShelterId(Long id, Pageable pageRequest);
}
