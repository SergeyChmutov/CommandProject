package com.example.users.conroller;

import com.example.users.model.Users;
import com.example.users.servis.UserServis;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServis servis;

    public UserController(UserServis servis) {
        this.servis = servis;
    }
    @GetMapping
    public List<Users> findAllUsers(){
       return servis.allUser();
    }
    @PostMapping
    public Users addUser(@RequestParam String name,@RequestParam Integer phone){
        return servis.addUser(name,phone);
    }
    @DeleteMapping("{id}")
    public String removeUser(@PathVariable Long id){
        return servis.removeUser(id);
    }
}
