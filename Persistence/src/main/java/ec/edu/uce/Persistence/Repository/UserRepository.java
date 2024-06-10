package ec.edu.uce.Persistence.Repository;

import ec.edu.uce.Persistence.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByNameAndPassword(String name, String password);
}
