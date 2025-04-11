package sit.int204.quizappapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.quizappapi.entities.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Integer> {
}