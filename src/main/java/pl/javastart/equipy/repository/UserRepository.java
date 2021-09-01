package pl.javastart.equipy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javastart.equipy.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByLastNameContainingIgnoreCase(String lastName);
    Optional<User> findByPesel(String pesel);
}
