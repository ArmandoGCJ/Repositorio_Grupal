package ec.edu.uce.Persistence.Services;

import ec.edu.uce.Persistence.Models.User;
import ec.edu.uce.Persistence.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void getByID(long id) {
        Optional<User> usuario = userRepository.findById(id);
        if (usuario.isPresent()) {
            System.out.println(usuario.get().toString());
        } else {
            System.out.println("No se encontró un usuario con el ID: " + id);
        }
    }

    public User findByUserAndPassword(String name, String password) {
        return userRepository.findByNameAndPassword(name, password);
    }

    public void update(User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(updatedUser.getId());
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            existingUser.setLife(updatedUser.getLife());
            existingUser.setScore(updatedUser.getScore());
            existingUser.setCurrentLevelIndex(updatedUser.getCurrentLevelIndex());
            existingUser.setNumEnemies(updatedUser.getNumEnemies());
            // Puedes actualizar otros campos aquí si es necesario
            userRepository.save(existingUser);
            System.out.println("Usuario actualizado correctamente");
        } else {
            System.out.println("No se encontró un usuario con el ID: " + updatedUser.getId() + " para actualizar");
        }
    }
}