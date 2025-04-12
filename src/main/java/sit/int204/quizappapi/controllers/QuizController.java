package sit.int204.quizappapi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int204.quizappapi.dtos.quiz.QuizDto;
import sit.int204.quizappapi.dtos.ResponseMessage;
import sit.int204.quizappapi.dtos.quiz.SimpleCommentDto;
import sit.int204.quizappapi.dtos.quiz.SimplePlayerProgressDto;
import sit.int204.quizappapi.dtos.quiz.createQuizDto;
import sit.int204.quizappapi.dtos.user.SimpleUserDto;
import sit.int204.quizappapi.entities.PlayerProgress;
import sit.int204.quizappapi.entities.Quiz;
import sit.int204.quizappapi.services.QuizService;
import sit.int204.quizappapi.utils.ListMapper;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/quizs")
public class QuizController {
    private final QuizService quizService;
    private final ModelMapper modelMapper;
    private final ListMapper listMapper;

    @Autowired
    public QuizController(QuizService quizService, ModelMapper modelMapper, ListMapper listMapper) {
        this.quizService = quizService;
        this.modelMapper = modelMapper;
        this.listMapper = listMapper;
    }

    @GetMapping
    public ResponseEntity<List<QuizDto>> getQuizs() {
        List<Quiz> quizs = quizService.findAll();
        return ResponseEntity.ok().body(
                listMapper.mapList(quizs, QuizDto.class, modelMapper)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> getQuiz(@PathVariable Integer id, @Valid @RequestBody SimpleUserDto user) {
        QuizDto quiz = modelMapper.map(quizService.findById(id), QuizDto.class);
        PlayerProgress progress = quizService.findPlayerProgress(quiz.getId(), user.getId());
        quiz.setPlayerProgress(modelMapper.map(progress, SimplePlayerProgressDto.class));
        return ResponseEntity.ok(quiz);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Set<SimpleCommentDto>> getQuizComments(@PathVariable Integer id) {
        Quiz quiz = quizService.findById(id);
        return ResponseEntity.ok().body(
                listMapper.mapSet(quiz.getComments(), SimpleCommentDto.class, modelMapper)
        );
    }

    @GetMapping("/{quizId}/progress/user/{userId}")
    public ResponseEntity<SimplePlayerProgressDto> getPlayerProgress(@PathVariable Integer quizId, @PathVariable Integer userId) {
        return ResponseEntity.ok(modelMapper.map(quizService.findPlayerProgress(quizId, userId), SimplePlayerProgressDto.class));
    }

    @PostMapping
    public ResponseEntity<QuizDto> createQuiz(@Valid @RequestBody createQuizDto quiz) {
        return ResponseEntity.ok(modelMapper.map(quizService.addQuiz(quiz), QuizDto.class));
    }

    @PostMapping("/{quizId}/progress/user/{userId}")
    public ResponseEntity<SimplePlayerProgressDto> addPlayerProgress(@PathVariable Integer quizId, @PathVariable Integer userId, @Valid @RequestBody SimplePlayerProgressDto playerProgress) {
        PlayerProgress progress = quizService.addPlayerProgress(quizId, userId , playerProgress);
        return ResponseEntity.ok(modelMapper.map(progress, SimplePlayerProgressDto.class));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<SimpleCommentDto> addComment(@PathVariable Integer id, @Valid @RequestBody SimpleCommentDto comment) {
        return ResponseEntity.ok(modelMapper.map(quizService.addComment(id, comment), SimpleCommentDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizDto> updateQuiz(@PathVariable Integer id, @Valid @RequestBody createQuizDto quiz) {
        quiz.setId(id);
        return ResponseEntity.ok(modelMapper.map(quizService.updateQuiz(quiz), QuizDto.class));
    }

    @PutMapping("/{quizId}/progress/user/{userId}")
    public ResponseEntity<SimplePlayerProgressDto> updatePlayerProgress(@PathVariable Integer quizId, @PathVariable Integer userId, @Valid @RequestBody SimplePlayerProgressDto playerProgress) {
        PlayerProgress progress = quizService.updatePlayerProgress(quizId, userId, playerProgress);
        return ResponseEntity.ok(modelMapper.map(progress, SimplePlayerProgressDto.class));
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<QuizDto> approvedQuiz(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(quizService.approvedQuiz(id), QuizDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> removeQuiz(@PathVariable Integer id) {
        String status = quizService.removeQuiz(id);
        return ResponseEntity.ok(new ResponseMessage(status));
    }
}
