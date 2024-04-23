package pro.dev.animalshelter.service;

import pro.dev.animalshelter.interfaces.AnimalInterface;
import pro.dev.animalshelter.model.Animal;
import pro.dev.animalshelter.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public class AnimalService implements AnimalInterface {
    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Animal addAnimal(Long idAnimal, String nameAnimal, int ageAnimal) {
        Animal animal = new Animal(idAnimal, nameAnimal, ageAnimal);
        animalRepository.save(animal);
        return animal;
    }

    @Override
    public List<Animal> allAnimal() {
        return animalRepository.findAll();
    }

    @Override
    public String removeAnimal(Long idAnimal) {
        animalRepository.deleteById(idAnimal);
        return "Животное удалено из базы данных.";
    }

    @Override
    public String clearAnimal() {
        animalRepository.deleteAll();
        return "База данных очищена";
    }

    @Override
    public Animal findByIdAnimal(Long idAnimal) {
        return animalRepository.findById(idAnimal).get();
    }

    @Override
    public Boolean existsByIdAnimal(Long idAnimal) {
        return animalRepository.existsById(idAnimal);
    }
}

