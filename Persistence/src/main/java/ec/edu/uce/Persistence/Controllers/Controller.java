package ec.edu.uce.Persistence.Controllers;

import ec.edu.uce.Persistence.Services.UserService;
import ec.edu.uce.Persistence.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/User")
public class Controller {
    @Autowired
    UserService userService;

    @GetMapping()
    public ArrayList<User> GetUsers(){
        return userService.readAll();
    }

    @PostMapping()
    public User SaveUser(@RequestBody User user){
        return this.userService.save(user);
    }


}
