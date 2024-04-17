package pro.dev.animalshelter.service;

import org.springframework.stereotype.Service;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.repository.ShelterRepository;

import java.util.Optional;

@Service
public class ShelterService {
    private final ShelterRepository repository;

    public ShelterService(ShelterRepository repository) {
        this.repository = repository;
    }

    public Shelter createShelter(String name) {
        Shelter shelter = new Shelter();
        shelter.setName(name);
        return repository.save(shelter);
    }
}
