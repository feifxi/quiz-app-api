package sit.int204.quizappapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.quizappapi.entities.PlayerProgress;
import sit.int204.quizappapi.entities.Quiz;
import sit.int204.quizappapi.entities.User;

public interface PlayerProgressRepository extends JpaRepository<PlayerProgress, Integer> {
    boolean existsByUser(User user);

    PlayerProgress findByUserAndQuiz(User user, Quiz quiz);
}