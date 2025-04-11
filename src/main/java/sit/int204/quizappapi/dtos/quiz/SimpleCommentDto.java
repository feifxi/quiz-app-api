package sit.int204.quizappapi.dtos.quiz;

import lombok.Data;
import sit.int204.quizappapi.dtos.user.SimpleUserDto;

@Data
public class SimpleCommentDto {
    private Integer id;
    private SimpleUserDto user;
    private String text;
}
