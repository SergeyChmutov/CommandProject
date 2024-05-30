package pro.dev.animalshelter.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.dev.animalshelter.conroller.ShelterController;
import pro.dev.animalshelter.interfaces.ShelterService;
import pro.dev.animalshelter.model.Animal;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.Users;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShelterController.class)
public class ShelterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShelterService shelterService;


    @Test
    public void shouldAddShelter() throws Exception {
        //given
        Long id = 1L;
        String name = "Wet nose";

        JSONObject shelterObject = new JSONObject();
        shelterObject.put("name", name);

        Shelter shelter = new Shelter();
        shelter.setId(id);
        shelter.setName(name);

        when(shelterService.addShelter(any(Shelter.class))).thenReturn(shelter);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/shelters")
                .content(shelterObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));

    }

    @Test
    public void shouldGetAllShelters() throws Exception {
        //given
        List<Shelter> shelters = new ArrayList<>();
        shelters.add(new Shelter("Wet nose"));
        shelters.add(new Shelter("Pug"));

        when(shelterService.getShelters()).thenReturn(shelters);
        //when
        //then

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/shelters")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
    @Test
    public void shouldGetShelterById() throws Exception {
        //given
        Long id = 1L;
        String name = "Wet nose";

        Shelter shelter = new Shelter();
        shelter.setId(id);
        shelter.setName(name);

        when(shelterService.getShelter(id)).thenReturn(shelter);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/shelters/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));

    }

    @Test
    public void shouldDeleteShelter() throws Exception {
        //given
        Long id = 1L;

        doNothing().when(shelterService).deleteShelter(id);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/shelters/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) ;

    }

    @Test
    public void shouldUpdateShelter() throws Exception {
        //given
        Long id = 1L;
        Shelter shelter = new Shelter("Wet nose");

        JSONObject shelterObject = new JSONObject();
        shelterObject.put("name", shelter.getName());

        when(shelterService.updateShelter(id, shelter)).thenReturn(shelter);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/shelters/{id}", id)
                        .content(shelterObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.name").value(shelter.getName()));
    }

    @Test
    public void shouldGetAllVolunteersByShelter() throws Exception {
        //given
        Long shelterId = 1L;
        List<Users> volunteers = new ArrayList<>();
        volunteers.add(new Users(1L, "Elena", "123", new Shelter("Wet nose")));
        volunteers.add(new Users(1L, "Ivan", "321", new Shelter("Wet nose")));

        when(shelterService.getVolunteers(shelterId)).thenReturn(volunteers);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/shelters/{id}/volunteers", shelterId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void shouldGetAllAnimalsByShelter() throws Exception {
        //given
        Long shelterId = 1L;
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat", 2, new Shelter("Wet nose")));
        animals.add(new Animal("Dog", 3, new Shelter("Wet nose")));


        when(shelterService.getAnimals(shelterId)).thenReturn(animals);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/shelters/{id}/animals", shelterId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

}
