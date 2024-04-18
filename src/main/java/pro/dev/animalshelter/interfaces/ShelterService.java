package pro.dev.animalshelter.interfaces;

import pro.dev.animalshelter.model.Animal;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.Users;

import java.util.List;

public interface ShelterService {
    Shelter addShelter(Shelter shelter);

    List<Shelter> getShelters();

    Shelter updateShelter(Long id, Shelter shelter);

    void deleteShelter(Long id);

    List<Users> getVolunteers(Long id);

    List<Animal> getAnimals(Long id);
}
