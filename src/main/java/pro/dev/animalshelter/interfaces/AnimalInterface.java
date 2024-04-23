package pro.dev.animalshelter.interfaces;

import pro.dev.animalshelter.model.Animal;

import java.util.List;

public interface AnimalInterface {
    Animal addAnimal (Long idAnimal, String nameAnimal, int ageAnimal);

    List<Animal> allAnimal();

    String removeAnimal(Long idAnimal);

    String clearAnimal();

    Animal findByIdAnimal(Long idAnimal);

    Boolean existsByIdAnimal(Long idAnimal);
}
