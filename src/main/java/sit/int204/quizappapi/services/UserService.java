package sit.int204.quizappapi.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int204.quizappapi.dtos.user.RegisterUserDto;
import sit.int204.quizappapi.dtos.user.SimpleUserDto;
import sit.int204.quizappapi.dtos.user.UserAuthRequest;
import sit.int204.quizappapi.entities.User;
import sit.int204.quizappapi.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findAll(Integer page, Integer size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public User findById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found")
        );
    }

    public User addUser(RegisterUserDto user) {
        if (userRepository.existsByNameOrEmail(user.getName(), user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user is already registered");
        }
        return userRepository.save(modelMapper.map(user, User.class));
    }

    public User updateUser(SimpleUserDto user) {
        if (!userRepository.existsById(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return userRepository.save(modelMapper.map(user, User.class));
    }

    public String removeUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(userId);
        return "User remove Successfully";
    }

    public User authenticateUser(UserAuthRequest user) {
//        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
//                user.getUsername(), user.getPassword()
//        );
        //401 if failed
//        authenticationManager.authenticate(upat);

//        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(user.getUsername());
//        long refreshTokenAgeInMinute = 8 * 60 * 60 * 1000; // 8 Hours
//        return new TokenResponse(
//                jwtUtils.generateToken(userDetails),
//                jwtUtils.generateToken(userDetails, refreshTokenAgeInMinute, TokenType.REFRESH_TOKEN)
//        );

        User existUser = userRepository.findByEmail(user.getEmail());
        if (existUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user is not register yet");
        }
        if (existUser.getPassword().equals(user.getPassword())) {
            return existUser;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect password");
        }
    }

//    public Map<String, String> refreshToken(String refreshToken) {
//        jwtUtils.verifyToken(refreshToken);
//        Map<String, Object> claims = jwtUtils.getJWTClaimsSet(refreshToken);
//        jwtUtils.isExpired(claims);
//        if (!jwtUtils.isValidClaims(claims) || !"REFRESH_TOKEN".equals(claims.get("typ"))) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
//        }
//        UserDetails userDetails = jwtUserDetailsService.loadUserById((Long) claims.get("uid"));
//        return Map.of("access_token", jwtUtils.generateToken(userDetails));
//    }
}
