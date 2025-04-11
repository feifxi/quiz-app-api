package sit.int204.quizappapi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int204.quizappapi.dtos.user.RegisterUserDto;
import sit.int204.quizappapi.dtos.user.SimpleUserDto;
import sit.int204.quizappapi.entities.User;
import sit.int204.quizappapi.services.UserService;
import sit.int204.quizappapi.utils.ListMapper;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ListMapper listMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, ListMapper listMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.listMapper = listMapper;
    }

    @GetMapping
    public ResponseEntity<List<SimpleUserDto>> getUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(
                listMapper.mapList(users, SimpleUserDto.class, modelMapper)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleUserDto> getUsers(@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(modelMapper.map(user, SimpleUserDto.class));
    }

    @PostMapping("/register")
    public ResponseEntity<SimpleUserDto> createUser(@Valid @RequestBody RegisterUserDto user) {
        user.setRole("user");
        User newUser = userService.addUser(user);
        return ResponseEntity.ok(modelMapper.map(newUser, SimpleUserDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleUserDto> updateUser(@PathVariable Integer id, @RequestBody SimpleUserDto user) {
        user.setId(id);
        return ResponseEntity.ok(modelMapper.map(user, SimpleUserDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeUser(@PathVariable Integer id) {
        String status = userService.removeUser(id);
        return ResponseEntity.ok(status);
    }


}
