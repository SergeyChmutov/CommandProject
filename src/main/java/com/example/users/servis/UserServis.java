package com.example.users.servis;

import com.example.users.model.Users;
import com.example.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServis implements UserInterface {
    private final UserRepository userRepository;

    public UserServis(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users addUser(String name, Integer phone) {
        Users a = new Users(name,phone);
        if (userRepository.findAll().indexOf(a) ==(-1)){
            userRepository.save(a);
        }

        return a;
    }
    @Override
    public List<Users> allUser() {
        return userRepository.findAll();
    }
    @Override
    public String removeUser(Long id) {
        if (id == -1l){
            userRepository.deleteAll();
            return "База данных очищена";
        }
        userRepository.deleteById(id);
        return "Пользователь удалён!";
    }


}
