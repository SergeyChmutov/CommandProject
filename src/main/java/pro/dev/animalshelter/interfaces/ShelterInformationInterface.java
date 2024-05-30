package pro.dev.animalshelter.interfaces;

import pro.dev.animalshelter.model.ShelterInformation;

public interface ShelterInformationInterface {
    ShelterInformation saveShelterInformationProperty(
            Long shelterId,
            String propertyName,
            String propertyValue
    );

    ShelterInformation getPropertyByShelterAndName(Long id, String propertyName);
}
