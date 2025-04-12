package sit.int204.quizappapi.dtos.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import sit.int204.quizappapi.dtos.user.SimpleUserDto;
import sit.int204.quizappapi.entities.Quiz;

@Data
public class SimpleCommentDto {
    private Integer id;
    @JsonIgnore
    private Quiz quiz;
    private SimpleUserDto user;
    private String text;
}
