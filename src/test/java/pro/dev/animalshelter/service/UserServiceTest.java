package pro.dev.animalshelter.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.dev.animalshelter.model.Users;
import pro.dev.animalshelter.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testAddUser() {
        Users user = new Users(1L, "John", "123456789",null);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        Users savedUser = userService.addUser(1L, "John", "123456789",null);

        assertEquals(user, savedUser);
    }

    @Test
    public void testAllUser() {
        List<Users> userList = new ArrayList<>();
        userList.add(new Users(1L, "John", "123456789",null));
        Mockito.when(userRepository.findAll()).thenReturn(userList);

        List<Users> retrievedUsers = userService.allUser();

        assertEquals(userList, retrievedUsers);
    }

    @Test
    public void testRemoveUser() {
        Long userId = 1L;
        Mockito.doNothing().when(userRepository).deleteById(userId);

        String result = userService.removeUser(userId);

        assertEquals("Пользователь удалён!", result);
    }

    @Test
    public void testClearUsers() {
        Mockito.doNothing().when(userRepository).deleteAll();

        String result = userService.clearUsers();

        assertEquals("База данных очищена", result);
    }

    @Test
    public void testFindById() {
        Users user = new Users(1L, "John", "123456789",null);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Users retrievedUser = userService.findById(1L);

        assertEquals(user, retrievedUser);
    }

    @Test
    public void testExistsById() {
        Mockito.when(userRepository.existsById(1L)).thenReturn(true);

        Boolean result = userService.existsById(1L);

        assertTrue(result);
    }
}