package sit.int204.quizappapi.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int204.quizappapi.dtos.quiz.SimpleCommentDto;
import sit.int204.quizappapi.entities.Comment;
import sit.int204.quizappapi.repositories.CommentRepository;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public CommentService(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Page<Comment> findAll(Integer page, Integer size) {
        return commentRepository.findAll(PageRequest.of(page, size));
    }

    public Comment findById(Integer progressId) {
        return commentRepository.findById(progressId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Comment not found")
        );
    }

    public Comment addComment(SimpleCommentDto comment) {
        return commentRepository.save(modelMapper.map(comment, Comment.class));
    }

    public Comment updateComment(Integer commentId, SimpleCommentDto comment) {
        if (!commentRepository.existsById(commentId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        }
        comment.setId(commentId);
        return commentRepository.save(modelMapper.map(comment, Comment.class));
    }

    public String removeComment(Integer commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        }
        commentRepository.deleteById(commentId);
        return "Comment remove successfully";
    }
}
