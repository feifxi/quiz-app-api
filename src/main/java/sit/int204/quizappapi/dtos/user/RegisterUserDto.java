package sit.int204.quizappapi.dtos.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterUserDto {
    @NotNull(message = "Name is required")
    @NotEmpty
    private String name;

    @NotNull(message = "Email is required")
    @NotEmpty
    private String email;

    @NotNull(message = "Password is required")
    @NotEmpty
    private String password;

    private String Role;
}
