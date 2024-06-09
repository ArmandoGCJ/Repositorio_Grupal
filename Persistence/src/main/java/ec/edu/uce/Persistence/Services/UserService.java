package ec.edu.uce.Persistence.Services;

import ec.edu.uce.Persistence.Models.User;
import ec.edu.uce.Persistence.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void save(User user) {

        userRepository.save(user);
    }

    public List<User> readAll() {
        return userRepository.findAll();
    }

    public void getByID(long id){
        Optional<User> usuario = userRepository.findById(id);
        if (usuario.isPresent()) {
            System.out.println(usuario.get().toString());
        } else {
            System.out.println("No se encontró un usuario con el ID: " + id);
        }
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public void update(User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(updatedUser.getId());
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setLife(updatedUser.getLife());
            existingUser.setScore(updatedUser.getScore());
            // Puedes actualizar otros campos aquí si es necesario
            userRepository.save(existingUser);
            System.out.println("Usuario actualizado correctamente");
        } else {
            System.out.println("No se encontró un usuario con el ID: " + updatedUser.getId() + " para actualizar");
        }
    }

}