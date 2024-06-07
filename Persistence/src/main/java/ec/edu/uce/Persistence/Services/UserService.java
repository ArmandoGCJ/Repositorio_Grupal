package ec.edu.uce.Persistence.Services;

import ec.edu.uce.Persistence.Models.User;
import ec.edu.uce.Persistence.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    @Autowired
    UserRepository repository;

    public void save(User user){
        repository.save(user);
    }

    public void readAll(){
        List<User> result = repository.findAll();
        result.forEach(e -> System.out.println(e.toString()));
    }

    public void getByID(long id){
        Optional<User> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            System.out.println(usuario.get().toString());
        } else {
            System.out.println("No se encontró un usuario con el ID: " + id);
        }
    }


    @GetMapping("/user/getuser")
    public List <User> miprimerEndPoint(  @RequestParam(name= "nombre")String name){
        List<User> users = new ArrayList<>();
        User user = new User(1, "David", "123david",123,154);
        users.add(user);
        return users;
    }

}
