package pro.dev.animalshelter.interfaces;


import pro.dev.animalshelter.model.Users;

import java.util.List;

public interface UserInterface {
    Users addUser(Long id, String name, Integer phone);

    List<Users> allUser();

    String removeUser(Long id);
}
