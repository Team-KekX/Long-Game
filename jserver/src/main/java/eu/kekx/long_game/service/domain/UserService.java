package eu.kekx.long_game.service;

import eu.kekx.long_game.domain.User;
import eu.kekx.long_game.persistence.user.UserRepository;
import eu.kekx.long_game.presentation.request.UserLoginRequest;
import eu.kekx.long_game.presentation.request.UserRequest;
import eu.kekx.long_game.presentation.response.UserLoginResponse;
import eu.kekx.long_game.service.exceptions.NotFoundException;
import eu.kekx.long_game.service.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public User createUser(UserRequest dto) {
        Objects.requireNonNull(dto, "UserRequest Body must not be null");
        DtoUtils.checkAllNullsAndBlanks(dto);

        if(userRepository.existsByEmail(dto.email())) 
            throw new IllegalArgumentException("Email " + dto.email() + " is already used by a different account.");
        if(userRepository.existsByUsername(dto.username())) 
            throw new IllegalArgumentException("Username " + dto.username() + " is already used by a different account.");

        var user = new User(dto.username(), dto.email(), dto.password(), passwordEncoder);
        return userRepository.save(user);
    }

    public UserLoginResponse login(UserLoginRequest dto) {
        Objects.requireNonNull(dto, "UserLoginRequest Body must not be null");
        DtoUtils.checkAllNullsAndBlanks(dto);

        val user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new NotFoundException("No user with email [" + dto.email() + "] found!"));

        if (!user.comparePasswords(dto.password(), passwordEncoder)) {
            throw new IllegalArgumentException("Invalid password");
        }

        String token = jwtService.generateToken(user);
        return new UserLoginResponse(user.getUsername(), user.getEmail(), token);
    }
}
