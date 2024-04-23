package pro.dev.animalshelter.conroller;

import org.springframework.web.bind.annotation.*;
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

    @DeleteMapping("{id}")
    public String removeUser(@PathVariable Long id) {
        return service.removeUser(id);
    }

    @DeleteMapping()
    public String clearUsers() {
        return service.clearUsers();
    }

    @GetMapping("{id}")
    public Users findById(@PathVariable Long id) {
        return service.findById(id);
    }
}

