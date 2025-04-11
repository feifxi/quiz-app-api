package sit.int204.quizappapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.quizappapi.entities.PlayerProgress;

public interface PlayerProgressRepository extends JpaRepository<PlayerProgress, Integer> {
}