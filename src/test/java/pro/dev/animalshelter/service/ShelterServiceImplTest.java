package pro.dev.animalshelter.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.dev.animalshelter.exception.SheltersNotFoundException;
import pro.dev.animalshelter.model.Animal;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.Users;
import pro.dev.animalshelter.repository.ShelterRepository;

import java.util.*;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShelterServiceImplTest {
    @ExtendWith(MockitoExtension.class)
    @Mock
    private ShelterRepository shelterRepository;

    @InjectMocks
    private SheltersServiceImpl sheltersService;

    @Test
    public void shouldAddShelter() {
        //given
        Shelter expectedShelter = new Shelter("Wet nose");
        when(shelterRepository.save(expectedShelter)).thenReturn(expectedShelter);

        //when
        Shelter actualShelter = sheltersService.addShelter(new Shelter("Wet nose"));

        //then
        assertEquals(expectedShelter, actualShelter);
    }

    @Test
    public void shouldGetAllShelters() {
        //given
        List<Shelter> shelters = new ArrayList<>();
        shelters.add(new Shelter("Wet nose"));
        shelters.add(new Shelter("Pug"));

        when(shelterRepository.findAll()).thenReturn(shelters);

        //when
        List<Shelter> actualShelters = sheltersService.getShelters();

        //then
        assertEquals(shelters, actualShelters);
    }

    @Test
    public void shouldUpdateShelter() {
        //given
        Long id = 1L;
        Shelter existingShelter = new Shelter();
        existingShelter.setName("Wet nose");
        existingShelter.setId(id);

        Shelter updatedShelter = new Shelter();
        updatedShelter.setName("New Name");

        when(shelterRepository.findById(id)).thenReturn(Optional.of(existingShelter));
        when(shelterRepository.save(existingShelter)).thenReturn(existingShelter);

        //when
        Shelter actualShelter = sheltersService.updateShelter(id, updatedShelter);

        //then
        assertNotNull(actualShelter);
        assertEquals(id, actualShelter.getId());
        assertEquals(updatedShelter.getName(), actualShelter.getName());
    }

    @Test
    public void shouldThrowExceptionWhenShelterNotFound() {
        // given
        Long id = 1L;
        Shelter updatedShelter = new Shelter();
        updatedShelter.setName("New Name");

        when(shelterRepository.findById(id)).thenThrow(SheltersNotFoundException.class);

        // when
        // then
        assertThrows(SheltersNotFoundException.class, () -> {
            sheltersService.updateShelter(id, updatedShelter);
        });
    }

    @Test
    public void shouldGetShelterById() {
        // given
        Long id = 1L;
        Shelter shelter = new Shelter("Wet nose");
        shelter.setId(id);

        when(shelterRepository.findById(id)).thenReturn(Optional.of(shelter));

        // when
        Shelter actualShelter = sheltersService.getShelter(id);

        // then
        assertNotNull(actualShelter);
        assertEquals(id, actualShelter.getId());
        assertEquals("Wet nose", actualShelter.getName());

    }

    @Test
    public void shouldDeleteShelter() {
        // given
        Long id = 1L;

        //when
        sheltersService.deleteShelter(id);

        //then
        verify(shelterRepository).deleteById(id);
    }

    @Test
    public void shouldGetAllVolunteersByShelter() {
        //given
        Long shelterId = 1L;
        List<Users> volunteers = new ArrayList<>();
        volunteers.add(new Users(1L, "Elena", "123", new Shelter("Wet nose")));
        volunteers.add(new Users(1L, "Ivan", "321", new Shelter("Wet nose")));
        Shelter shelter = new Shelter();
        shelter.setId(shelterId);
        shelter.setVolunteers(volunteers);

        when(shelterRepository.findById(shelterId)).thenReturn(Optional.of(shelter));

        //when
        List<Users> actualVolunteers = sheltersService.getVolunteers(shelterId);

        //then
        assertNotNull(actualVolunteers);
        assertEquals(2, actualVolunteers.size());
        assertEquals("Elena", actualVolunteers.get(0).getName());
        assertEquals("Ivan", actualVolunteers.get(1).getName());
    }
    @Test
    public void shouldGetAllAnimalsByShelter() {
        //given
        Long shelterId = 1L;
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat", 2, new Shelter("Wet nose")));
        animals.add(new Animal("Dog", 3, new Shelter("Wet nose")));

        Shelter shelter = new Shelter();
        shelter.setId(shelterId);
        shelter.setAnimals(animals);

        when(shelterRepository.findById(shelterId)).thenReturn(Optional.of(shelter));

        //when
        List<Animal> actualAnimals = sheltersService.getAnimals(shelterId);

        // then
        assertNotNull(actualAnimals);
        assertEquals(2, actualAnimals.size());
        assertEquals("Cat", actualAnimals.get(0).getName());
        assertEquals("Dog", actualAnimals.get(1).getName());
        assertEquals(2, actualAnimals.get(0).getAgeAnimal());
        assertEquals(3, actualAnimals.get(1).getAgeAnimal());

    }
}
