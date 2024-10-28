package eu.kekx.long_game.persistence.user;

import eu.kekx.long_game.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> queryById(Long id);
    Optional<User> queryByUsername(String username);
    Optional<User> queryByEmail(String email);
}
