package sit.int204.quizappapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.quizappapi.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByNameOrEmail(String name, String email);
    User findByEmail(String email);
}