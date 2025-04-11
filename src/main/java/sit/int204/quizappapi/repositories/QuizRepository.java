package sit.int204.quizappapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.quizappapi.entities.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    boolean existsByTitle(String title);
}