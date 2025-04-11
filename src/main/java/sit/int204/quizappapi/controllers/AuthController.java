package sit.int204.quizappapi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int204.quizappapi.dtos.user.SimpleUserDto;
import sit.int204.quizappapi.dtos.user.UserAuthRequest;
import sit.int204.quizappapi.entities.User;
import sit.int204.quizappapi.services.UserService;
import sit.int204.quizappapi.utils.ListMapper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private UserService userService;
    private final ModelMapper modelMapper;
    private final ListMapper listMapper;

    @Autowired
    AuthController(UserService userService, ModelMapper modelMapper, ListMapper listMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.listMapper = listMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<SimpleUserDto> login(@Valid @RequestBody UserAuthRequest user) {
        User authenticateUser = userService.authenticateUser(user);
        return ResponseEntity.ok(modelMapper.map(authenticateUser, SimpleUserDto.class));
    }

//    @PostMapping("/refresh-token")
//    public ResponseEntity<Object> refreshToken(@RequestHeader("x-refresh-token") String refreshToken) {
//        return ResponseEntity.ok(userService.refreshToken(refreshToken));
//    }
}
