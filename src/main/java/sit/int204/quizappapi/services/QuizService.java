package sit.int204.quizappapi.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int204.quizappapi.dtos.quiz.createQuizDto;
import sit.int204.quizappapi.entities.Quiz;
import sit.int204.quizappapi.repositories.QuizRepository;

import java.util.List;
@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public QuizService(QuizRepository quizRepository, ModelMapper modelMapper) {
        this.quizRepository = quizRepository;
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
        return quizRepository.save(modelMapper.map(quiz, Quiz.class));
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
}
