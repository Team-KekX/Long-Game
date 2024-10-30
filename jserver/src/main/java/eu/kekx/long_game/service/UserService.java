package eu.kekx.long_game.service;

import eu.kekx.long_game.domain.User;
import eu.kekx.long_game.persistence.user.UserRepository;
import eu.kekx.long_game.presentation.request.UserRequest;
import eu.kekx.long_game.service.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public void createUser(UserRequest dto) {
        Objects.requireNonNull(dto, "UserRequest Body must not be null");

        DtoUtils.checkAllNullsAndBlanks(dto);

        if(userRepository.existsByEmail(dto.email())) throw new IllegalArgumentException("Email " + dto.email() + "is already used by a different account.");
        if(userRepository.existsByUsername(dto.username())) throw new IllegalArgumentException("Username " + dto.username() + "is already used by a different account.");

        var user = new User(dto.username(), dto.email(), dto.password());

        userRepository.save(user);
    }

}
