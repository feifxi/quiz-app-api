package sit.int204.quizappapi.dtos.quiz;

import jakarta.persistence.*;
import lombok.Data;
import sit.int204.quizappapi.entities.Quiz;
@Data
public class SimpleLevelDto {
    private Integer id;

    private String template;

    private String question;

    private String questionImage;

    private String correctChoice;

    private String incorrectChoice1;

    private String incorrectChoice2;

    private String incorrectChoice3;
}
