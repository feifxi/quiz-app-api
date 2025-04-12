package sit.int204.quizappapi.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int204.quizappapi.dtos.quiz.SimplePlayerProgressDto;
import sit.int204.quizappapi.dtos.user.SimpleUserDto;
import sit.int204.quizappapi.entities.PlayerProgress;
import sit.int204.quizappapi.entities.Quiz;
import sit.int204.quizappapi.entities.User;
import sit.int204.quizappapi.repositories.PlayerProgressRepository;

import java.util.List;

@Service
public class PlayerProgressService {
    private final PlayerProgressRepository playerProgressRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public PlayerProgressService(PlayerProgressRepository playerProgressRepository, ModelMapper modelMapper) {
        this.playerProgressRepository = playerProgressRepository;
        this.modelMapper = modelMapper;
    }

    public List<PlayerProgress> findAll() {
        return playerProgressRepository.findAll();
    }

    public Page<PlayerProgress> findAll(Integer page, Integer size) {
        return playerProgressRepository.findAll(PageRequest.of(page, size));
    }

    public PlayerProgress findById(Integer progressId) {
        return playerProgressRepository.findById(progressId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Player progress not found")
        );
    }

    public PlayerProgress findByUserAndQuiz (User user, Quiz quiz) {
        PlayerProgress progress = playerProgressRepository.findByUserAndQuiz(user, quiz);
        if (progress == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Player progress not found");
        }
        return progress;
    }

    public PlayerProgress addPlayerProgress(SimplePlayerProgressDto playerProgress) {
        if (playerProgressRepository.existsByUser(modelMapper.map(playerProgress.getUser(), User.class))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This player progress is already exist");
        }
        return playerProgressRepository.save(modelMapper.map(playerProgress, PlayerProgress.class));
    }

    public PlayerProgress updatePlayerProgress(Integer progressId, SimplePlayerProgressDto playerProgress) {
        if (!playerProgressRepository.existsById(progressId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player progress not found");
        }
        playerProgress.setId(progressId);
        return playerProgressRepository.save(modelMapper.map(playerProgress, PlayerProgress.class));
    }

    public String removePlayerProgress(Integer progressId) {
        if (!playerProgressRepository.existsById(progressId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player progress not found");
        }
        playerProgressRepository.deleteById(progressId);
        return "Player progress remove successfully";
    }
}
