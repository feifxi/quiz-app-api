package sit.int204.quizappapi.dtos.quiz;

import lombok.Data;
import sit.int204.quizappapi.dtos.user.SimpleUserDto;

@Data
public class SimpleReactionDto {
    private Integer id;
    private SimpleUserDto user;
}
