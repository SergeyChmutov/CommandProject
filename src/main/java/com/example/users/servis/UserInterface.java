package com.example.users.servis;


import com.example.users.model.Users;

import java.util.List;

public interface UserInterface {
    Users addUser(String name, Integer phone);
    List<Users> allUser();
    String removeUser(Long id);
}
