package pro.dev.animalshelter.service;

import org.springframework.stereotype.Service;
import pro.dev.animalshelter.enums.ShelterInformationProperty;
import pro.dev.animalshelter.exception.ShelterInformationNotFoundException;
import pro.dev.animalshelter.interfaces.ShelterInformationInterface;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.ShelterInformation;
import pro.dev.animalshelter.repository.ShelterInformationRepository;
import pro.dev.animalshelter.repository.ShelterRepository;

@Service
public class ShelterInformationService implements ShelterInformationInterface {
    private final ShelterInformationRepository repository;

    public ShelterInformationService(ShelterInformationRepository repository, ShelterRepository shelterRepository) {
        this.repository = repository;
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

    public ShelterInformation getPropertyByShelterAndName(Long id, String propertyName) {
        return repository.findByPkShelterIdAndPkShelterInformationProperty(
                id,
                ShelterInformationProperty.getPropertyByName(propertyName.toUpperCase())
        ).orElseThrow(
                () -> new ShelterInformationNotFoundException(
                        "Не заполнена информация по свойству " + propertyName + " приюта с идентификатором " + id)
        );
    }
}
