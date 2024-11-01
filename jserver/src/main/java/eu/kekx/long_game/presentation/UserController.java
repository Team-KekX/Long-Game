package eu.kekx.long_game.presentation;

import eu.kekx.long_game.presentation.request.UserLoginRequest;
import eu.kekx.long_game.presentation.request.UserRequest;
import eu.kekx.long_game.presentation.response.UserLoginResponse;
import eu.kekx.long_game.service.domain.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@Slf4j
@RestController
@RequestMapping(UserController.API_PATH)
public class UserController {
    
    public static final String API_PATH = "/api/v1/users";
    
    private final UserService userService;

    @PostMapping("/register")
    public HttpEntity<Void> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }
}
