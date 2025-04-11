package sit.int204.quizappapi.dtos.quiz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import sit.int204.quizappapi.dtos.user.SimpleUserDto;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class createQuizDto {
    private Integer id;

    private String title;

    private String thumbnail;

    private String description;

    private String status;

    private SimpleUserDto createBy;

    private Set<SimpleLevelDto> levels = new LinkedHashSet<>();
}
