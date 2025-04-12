package sit.int204.quizappapi.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int204.quizappapi.dtos.quiz.SimpleCommentDto;
import sit.int204.quizappapi.dtos.quiz.SimplePlayerProgressDto;
import sit.int204.quizappapi.dtos.quiz.createQuizDto;
import sit.int204.quizappapi.dtos.user.SimpleUserDto;
import sit.int204.quizappapi.entities.Comment;
import sit.int204.quizappapi.entities.PlayerProgress;
import sit.int204.quizappapi.entities.Quiz;
import sit.int204.quizappapi.entities.User;
import sit.int204.quizappapi.repositories.QuizRepository;

import java.util.List;
@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final CommentService commentService;
    private final UserService userService;
    private final PlayerProgressService playerProgressService;
    private final ModelMapper modelMapper;


    @Autowired
    public QuizService(QuizRepository quizRepository, CommentService commentService, UserService userService, PlayerProgressService playerProgressService , ModelMapper modelMapper) {
        this.quizRepository = quizRepository;
        this.commentService = commentService;
        this.playerProgressService = playerProgressService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    public Page<Quiz> findAll(Integer page, Integer size) {
        return quizRepository.findAll(PageRequest.of(page, size));
    }

    public Quiz findById(Integer id) {
        return quizRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Quiz not found")
        );
    }

    public Quiz addQuiz(createQuizDto quiz) {
        if (quizRepository.existsByTitle(quiz.getTitle())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are quiz with the same title");
        }
        quiz.setStatus("pending");
        return quizRepository.save(modelMapper.map(quiz, Quiz.class));
    }

    public Comment addComment(Integer quizID , SimpleCommentDto comment) {
        Quiz quiz = quizRepository.findById(quizID).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Quiz not found")
        );
        comment.setQuiz(quiz);
        return commentService.addComment(comment);
    }

    public Quiz updateQuiz(createQuizDto quiz) {
        if (!quizRepository.existsById(quiz.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found");
        }
        return quizRepository.save(modelMapper.map(quiz, Quiz.class));
    }

    public String removeQuiz(Integer id) {
        if (!quizRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found");
        }
        quizRepository.deleteById(id);
        return "Quiz remove Successfully";
    }

    public Quiz approvedQuiz(Integer id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Quiz not found")
        );
        quiz.setStatus("publish");
        return quizRepository.save(quiz);
    }

    public PlayerProgress findPlayerProgress (Integer quizId, Integer userId) {
        Quiz quiz = findById(quizId);
        User user = userService.findById(userId);
        return playerProgressService.findByUserAndQuiz(user, quiz);
    }

    public PlayerProgress addPlayerProgress(Integer quizId, Integer userId, SimplePlayerProgressDto playerProgress) {
        Quiz quiz = findById(quizId);
        User user = userService.findById(userId);
        playerProgress.setQuiz(quiz);
        playerProgress.setUser(modelMapper.map(user, SimpleUserDto.class));
        return playerProgressService.addPlayerProgress(playerProgress);
    }

    public PlayerProgress updatePlayerProgress(Integer quizId, Integer userId, SimplePlayerProgressDto playerProgress) {
        PlayerProgress currentProgress = findPlayerProgress(userId, quizId);
        currentProgress.setStar(playerProgress.getStar());
        return playerProgressService.updatePlayerProgress(currentProgress.getId(), modelMapper.map(currentProgress, SimplePlayerProgressDto.class));
    }

}
