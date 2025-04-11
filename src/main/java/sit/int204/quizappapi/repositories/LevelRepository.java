package sit.int204.quizappapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.quizappapi.entities.Level;

public interface LevelRepository extends JpaRepository<Level, Integer> {
}