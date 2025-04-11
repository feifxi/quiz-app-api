package sit.int204.quizappapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.quizappapi.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}