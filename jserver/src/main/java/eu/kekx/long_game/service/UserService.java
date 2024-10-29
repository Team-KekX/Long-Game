package eu.kekx.long_game.service;

import eu.kekx.long_game.domain.User;
import eu.kekx.long_game.presentation.request.UserRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import eu.kekx.long_game.persistence.user.*;

import java.util.Objects;

@RequiredArgsConstructor

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public User createUser(UserRequest dto) {
        Objects.requireNonNull(dto, "UserRequest Body must not be null");

        if(dto.username() == null || dto.username().isBlank()) throw new NullPointerException("Username must not be null.");
        if(dto.email() == null || dto.email().isBlank()) throw new NullPointerException("Email must not be null.");
        if(dto.password() == null || dto.password().isBlank()) throw new NullPointerException("Username must not be null.");

        if(userRepository.existsByEmail(dto.email())) throw new IllegalArgumentException("Email " + dto.email() + "is already used by a different account.");
        if(userRepository.existsByUsername(dto.username())) throw new IllegalArgumentException("Username " + dto.username() + "is already used by a different account.");

        var user = new User(dto.username(), dto.email(), dto.password());

        return userRepository.save(user);
    }
}
