package pro.dev.animalshelter.service;

import org.springframework.stereotype.Service;
import pro.dev.animalshelter.exception.SheltersNotFoundException;
import pro.dev.animalshelter.interfaces.ShelterService;
import pro.dev.animalshelter.model.Animal;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.Users;
import pro.dev.animalshelter.repository.ShelterRepository;

import java.util.List;
@Service
public class SheltersServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;

    public SheltersServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @Override
    public Shelter addShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    @Override
    public List<Shelter> getShelters() {
        return shelterRepository.findAll();
    }

    @Override
    public Shelter updateShelter(Long id, Shelter shelter) {
        return shelterRepository.findById(id).map(shelterFromDB -> {
            shelterFromDB.setName(shelter.getName());
            return shelterRepository.save(shelterFromDB);
        }).orElseThrow(() -> new SheltersNotFoundException("Приют не найден"));
    }

    @Override
    public Shelter getShelter(Long id) {
        return shelterRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteShelter(Long id) {
        shelterRepository.deleteById(id);
    }

    @Override
    public List<Users> getVolunteers(Long id) {
        return shelterRepository.findById(id)
                .map(Shelter::getVolunteers)
                .orElseThrow(() -> new SheltersNotFoundException("Приют не найден"));
    }

    @Override
    public List<Animal> getAnimals(Long id) {
        return shelterRepository.findById(id)
                .map(Shelter::getAnimals)
                .orElseThrow(() -> new SheltersNotFoundException("Приют не найден"));
    }
}
