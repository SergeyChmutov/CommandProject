package pro.dev.animalshelter.interfaces;


import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.Users;

import java.util.List;

public interface UserInterface {
    Users addVolunteerUser(Long id, Long idShelter);

    Users addUser(Long id, String name, String phone, Shelter shelter);

    Users updateUser(Users user);

    List<Users> allUser();

    String removeUser(Long id);

    String clearUsers();

    Users findById(Long id);

    Boolean existsById(Long id);
}
