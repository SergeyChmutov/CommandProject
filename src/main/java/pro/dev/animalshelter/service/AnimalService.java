package pro.dev.animalshelter.service;

import org.springframework.data.domain.Pageable;
import pro.dev.animalshelter.interfaces.AnimalInterface;
import pro.dev.animalshelter.interfaces.ShelterService;
import pro.dev.animalshelter.model.Animal;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService implements AnimalInterface {
    private final AnimalRepository animalRepository;
    private final ShelterService shelterService;

    public AnimalService(AnimalRepository animalRepository, ShelterService shelterService) {
        this.animalRepository = animalRepository;
        this.shelterService = shelterService;
    }

    @Override
    public Animal addAnimal(Long idShelter, String nameAnimal, int ageAnimal) {
        final Shelter shelter = shelterService.getShelter(idShelter);
        Animal animal = new Animal(nameAnimal, ageAnimal, shelter);
        animal.setShelter(shelter);
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

    @Override
    public Long animalCountByShelterId(Long id) {
        return animalRepository.countByShelter_Id(id);
    }

    @Override
    public List<Animal> getPaginatedAnimalByShelterId(Long id, Pageable pageRequest) {
        return animalRepository.findByShelter_Id(id, pageRequest);
    }
}

