package pro.dev.animalshelter.service;

import org.springframework.stereotype.Service;
import pro.dev.animalshelter.exception.SheltersNotFoundException;
import pro.dev.animalshelter.exception.UserNotFoundException;
import pro.dev.animalshelter.interfaces.UserInterface;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.Users;
import pro.dev.animalshelter.repository.ShelterRepository;
import pro.dev.animalshelter.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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
        final Optional<Users> user = userRepository.findById(id);
        if (user.isPresent()) {
            if (idShelter == 0) {
                user.get().setShelter(null);
            } else {
                final Optional<Shelter> shelter = shelterRepository.findById(idShelter);
                if (shelter.isPresent()) {
                    user.get().setShelter(shelter.get());
                } else {
                    throw new SheltersNotFoundException("Не найден приют с идентификатором " + idShelter);
                }
            }
        } else {
            throw new UserNotFoundException(id);
        }
        userRepository.save(user.get());
        return user.get();
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
