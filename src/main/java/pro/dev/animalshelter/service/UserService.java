package pro.dev.animalshelter.service;

import pro.dev.animalshelter.interfaces.UserInterface;
import pro.dev.animalshelter.model.Users;
import pro.dev.animalshelter.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserInterface {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public Users addUser(Long id, String name, Integer phone) {
//        Users a = new Users(id, name, phone);
//        if (!userRepository.findAll().contains(a)){
//            userRepository.save(a);
//        }
//
//        return a;
//    }
    @Override
    public List<Users> allUser() {
        return userRepository.findAll();
    }
    @Override
    public String removeUser(Long id) {
        userRepository.deleteById(id);
        return "Пользователь удалён!";
    }

    @Override
    public String clearUsers() {
        userRepository.deleteAll();
        return "База данных очищена";
    }
    @Override
    public Users findById(Long id) {
        return userRepository.findById(id).get() ;
    }
}
