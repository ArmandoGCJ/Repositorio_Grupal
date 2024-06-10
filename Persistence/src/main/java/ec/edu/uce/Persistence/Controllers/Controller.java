package ec.edu.uce.Persistence.Controllers;

import ec.edu.uce.Persistence.Services.UserService;
import ec.edu.uce.Persistence.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping(value = "/updateUser")
    public User updateUser(@RequestParam String name, @RequestParam String password, @RequestBody User updatedUser) {
        User foundUser = userService.findByUserAndPassword(name, password);
        if (foundUser == null) {
            throw new RuntimeException("User no encontrado");
        }
        // Actualizar los campos restantes
        foundUser.setId(updatedUser.getId());
        foundUser.setLife(updatedUser.getLife());
        foundUser.setScore(updatedUser.getScore());
        foundUser.setCurrentLevelIndex(updatedUser.getCurrentLevelIndex());
        foundUser.setNumEnemies(updatedUser.getNumEnemies());

        userService.save(foundUser); // Guardar los cambios

        return foundUser;
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }
}
