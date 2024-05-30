package pro.dev.animalshelter.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.dev.animalshelter.enums.ShelterInformationProperty;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.ShelterInformation;
import pro.dev.animalshelter.model.Users;
import pro.dev.animalshelter.repository.ShelterInformationRepository;
import pro.dev.animalshelter.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;


public class ShelterInformationServiceTest {

    @Test
    public void saveShelterInformationProperty()

    {
        Long shelterId = 12l;
        String propertyName = "PASS";
        String propertyValue = "asdfg";
        Shelter shelter = new Shelter("sas");

        shelter.setId(shelterId);
        ShelterInformationProperty property = ShelterInformationProperty.valueOf(propertyName);

        ShelterInformation information = new ShelterInformation(shelter, property, propertyValue);
        ShelterInformation information1 = new ShelterInformation(shelter, ShelterInformationProperty.valueOf(propertyName), propertyValue);

        assertEquals(information, information1);
        assertNotNull(information);
    }

}
