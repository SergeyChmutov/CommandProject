package pro.dev.animalshelter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.dev.animalshelter.conroller.UserController;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.Users;
import pro.dev.animalshelter.service.SheltersServiceImpl;
import pro.dev.animalshelter.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;
    @MockBean
    private Shelter shelters;
    @MockBean
    private SheltersServiceImpl service1;

    Long id = 12345l;
    String name = "Иван";
    String  phone = "123456789";
    Shelter shelter = null;

    @Test
    public void findById() throws Exception{


        Users studentObject = new Users(id,name,phone,shelter);
        Users student = new Users(id,name,phone,shelter);

        when(service.findById(studentObject.getId())).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/12345"))

                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.phone").value(student.getPhone()));
    }
    @Test
    public void clearUsers() throws Exception{


        Users studentObject = new Users(id,name,phone,shelter);
        Users student = new Users(id,name,phone,shelter);

        when(service.clearUsers()).thenReturn("База данных очищена");

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users"))

                .andExpect(status().isOk());
    }
    @Test
    public void removeUser() throws Exception{


        Users studentObject = new Users(id,name,phone,shelter);


        when(service.removeUser(studentObject.getId())).thenReturn("Пользователь удалён!");

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/12345"))
                .andExpect(status().isOk());

    }
    @Test
    public void findAllUsers() throws Exception{

        Users student = new Users(id,name,phone,shelter);
        Users student1 = new Users(12l,"name","876543456",null);
        Users student2 = new Users(7654l,"ffff","234567098",null);
        List<Users> list = new ArrayList<>();
        list.add(student);
        list.add(student1);
        list.add(student2);

        when(service.allUser()).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

}
