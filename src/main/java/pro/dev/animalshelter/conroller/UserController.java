package pro.dev.animalshelter.conroller;

import pro.dev.animalshelter.model.Users;
import pro.dev.animalshelter.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<Users> findAllUsers(){
       return service.allUser();
    }

    @PostMapping
    public Users addUser(@RequestParam Long id, @RequestParam String name, @RequestParam Integer phone) {
        return service.addUser(id, name, phone);
    }

    @DeleteMapping("{id}")
    public String removeUser(@PathVariable Long id){
        return service.removeUser(id);
    }
}
