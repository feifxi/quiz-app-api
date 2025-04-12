package sit.int204.quizappapi.dtos.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import sit.int204.quizappapi.dtos.user.SimpleUserDto;
import sit.int204.quizappapi.entities.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class QuizDto {
    private Integer id;

    private String title;

    private String thumbnail;

    private String description;

    private String status;

    private SimpleUserDto createBy;

    private Set<SimpleLevelDto> levels = new LinkedHashSet<>();

    @JsonIgnore
    private Set<SimpleReactionDto> reactions = new LinkedHashSet<>();

    public int getReactionsCount() {
        return reactions.size();
    }

    // for each own user request
    private SimplePlayerProgressDto playerProgress;
}
