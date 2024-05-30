package pro.dev.animalshelter.conroller;

import org.springframework.web.bind.annotation.*;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.Users;
import pro.dev.animalshelter.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<Users> findAllUsers() {
        return service.allUser();
    }

    @PostMapping("{idUser}")
    public Users addVolunteerUser(@PathVariable(name = "idUser") Long idVolunteerAdd, @RequestParam Long idShelter) {
        return service.addVolunteerUser(idVolunteerAdd, idShelter);
    }
    @DeleteMapping("{idRemoveUser}")
    public String removeUser(@PathVariable Long idRemoveUser) {
        return service.removeUser(idRemoveUser);
    }

    @DeleteMapping()
    public String clearUsers() {
        return service.clearUsers();
    }

    @GetMapping("{idFind}")
    public Users findById(@PathVariable Long idFind) {
        return service.findById(idFind);
    }
}

