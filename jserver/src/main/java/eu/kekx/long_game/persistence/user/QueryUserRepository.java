package eu.kekx.long_game.persistence.user;

import java.util.Objects;
import java.util.Optional;

import eu.kekx.long_game.domain.QUser;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import eu.kekx.long_game.domain.User;

@Slf4j
@Repository
public class QueryUserRepository extends QuerydslRepositorySupport implements UserRepository {

    public QueryUserRepository() {
        super(User.class);
    }

    @Override
    public Optional<User> queryById(Long id) {
        Objects.requireNonNull(id, "id must not be null");
        QUser user = QUser.user;

        val fetchedUser = from(user)
                .where(user.id.eq(id))
                .fetchFirst();

        return Optional.ofNullable(fetchedUser);
    }

    @Override
    public Optional<User> queryByUsername(String username) {
        Objects.requireNonNull(username, "Username must not be null");

        QUser user = QUser.user;

        val fetchedUser = from(user)
                .where(user.username.eq(username))
                .fetchFirst();

        return Optional.ofNullable(fetchedUser);
    }

    @Override
    public Optional<User> queryByEmail(String email) {
        Objects.requireNonNull(email, "Email must not be null");

        QUser user = QUser.user;

        val fetchedUser = from(user)
                .where(user.email.eq(email))
                .fetchFirst();

        return Optional.ofNullable(fetchedUser);
    }
}
