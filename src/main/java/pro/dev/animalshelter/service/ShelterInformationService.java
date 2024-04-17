package pro.dev.animalshelter.service;

import org.springframework.stereotype.Service;
import pro.dev.animalshelter.enums.ShelterInformationProperty;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.ShelterInformation;
import pro.dev.animalshelter.repository.ShelterInformationRepository;
import pro.dev.animalshelter.repository.ShelterRepository;

@Service
public class ShelterInformationService {
    private final ShelterInformationRepository repository;
    private ShelterRepository shelterRepository;

    public ShelterInformationService(ShelterInformationRepository repository, ShelterRepository shelterRepository) {
        this.repository = repository;
        this.shelterRepository = shelterRepository;
    }

    public ShelterInformation saveShelterInformationProperty(
            Long shelterId,
            String propertyName,
            String propertyValue
    ) {
        Shelter shelter = new Shelter();
        shelter.setId(shelterId);
        ShelterInformationProperty property = ShelterInformationProperty.getPropertyByName(propertyName.toUpperCase());
        ShelterInformation information = new ShelterInformation(shelter, property, propertyValue);
        repository.save(information);
        return information;
    }
}
