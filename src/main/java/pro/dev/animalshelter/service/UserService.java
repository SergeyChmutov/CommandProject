package pro.dev.animalshelter.service;

import org.springframework.stereotype.Service;
import pro.dev.animalshelter.interfaces.UserInterface;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.Users;
import pro.dev.animalshelter.repository.ShelterRepository;
import pro.dev.animalshelter.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements UserInterface {
    private final UserRepository userRepository;
    private final ShelterRepository shelterRepository;

    public UserService(UserRepository userRepository, ShelterRepository shelterRepository) {
        this.userRepository = userRepository;
        this.shelterRepository = shelterRepository;
    }

    @Override
    public Users addVolunteerUser(Long id, Long idShelter) {
        if (idShelter == 0) {
            userRepository.findById(id).get().setShelter(null);
        } else {
            userRepository.findById(id).get().setShelter(shelterRepository.findById(idShelter).get());
        }
        userRepository.save(userRepository.findById(id).get());
        return userRepository.findById(id).get();
    }

    @Override
    public Users addUser(Long id, String name, String phone, Shelter shelter) {
        Users users = new Users(id, name, phone, shelter);
        userRepository.save(users);
        return users;
    }

    @Override
    public Users updateUser(Users user) {
        return userRepository.save(user);
    }

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
        return userRepository.findById(id).get();
    }

    @Override
    public Boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
