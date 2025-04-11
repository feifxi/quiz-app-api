package sit.int204.quizappapi.dtos.quiz;

import jakarta.persistence.Column;
import lombok.Data;
import sit.int204.quizappapi.dtos.user.SimpleUserDto;

@Data
public class SimplePlayerProgressDto {
    private SimpleUserDto user;
    private Integer star;
}
