package ec.edu.uce.Persistence.Repository;

import ec.edu.uce.Persistence.Models.User;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByName(String lastname);
}
