package ec.edu.uce.Persistence.Controllers;

import ec.edu.uce.Persistence.Services.UserService;
import ec.edu.uce.Persistence.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class Controller {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.readAll();
    }

    @PostMapping("/save")
    public void saveUser(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping("/getuser")
    public List<User> getUserByName(@RequestParam(name = "nombre") String name) {
        return userService.findByName(name);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }
}
