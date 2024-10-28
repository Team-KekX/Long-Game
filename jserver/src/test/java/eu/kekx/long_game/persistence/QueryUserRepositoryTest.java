package eu.kekx.long_game.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;

import eu.kekx.long_game.domain.User;
import eu.kekx.long_game.persistence.user.QueryUserRepository;

@ActiveProfiles("test")
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
public class QueryUserRepositoryTest {
    
    @Autowired
    QueryUserRepository queryUserRepository;

    @Autowired
    TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUsername("user" + i);
            user.setEmail("user" + i + "@example.com");
            user.setPassword("password" + i);
            entityManager.persist(user);
        }
        entityManager.flush();
    }

    @Test
    void queryById_WhenIdDoesNotExist_ReturnsEmptyOptional() {
        Optional<User> result = queryUserRepository.queryById(999L);
        
        assertTrue(result.isEmpty());
    }

    @Test
    void queryById_WhenIdExists_ReturnsUser() {
        User user1 = new User();
        user1.setUsername("userZ");
        user1.setEmail("user@example.com");
        user1.setPassword("password");
        user1 = entityManager.persist(user1);
        entityManager.flush();

        Optional<User> result = queryUserRepository.queryById(user1.getId());
        
        assertTrue(result.isPresent());
        assertEquals(user1.getId(), result.get().getId());
        assertEquals(user1.getUsername(), result.get().getUsername());
        assertEquals(user1.getEmail(), result.get().getEmail());
    }

    @Test
    void queryByUsername_WhenUsernameDoesNotExist_ReturnsEmptyOptional() {
        Optional<User> result = queryUserRepository.queryByUsername("nonexistent");
        
        assertTrue(result.isEmpty());
    }

    @Test
    void queryByUsername_WhenUsernameExists_ReturnsUser() {
        String username = "user1";
        User user = entityManager.getEntityManager().createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        
        Optional<User> result = queryUserRepository.queryByUsername(username);
        
        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getId());
        assertEquals(user.getUsername(), result.get().getUsername());
        assertEquals(user.getEmail(), result.get().getEmail());
    }

    @Test
    void queryByEmail_WhenEmailDoesNotExist_ReturnsEmptyOptional() {
        Optional<User> result = queryUserRepository.queryByEmail("nonexistent@example.com");
        
        assertTrue(result.isEmpty());
    }

    @Test
    void queryByEmail_WhenEmailExists_ReturnsUser() {
        String email = "user1@example.com";
        User user = entityManager.getEntityManager().createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
        
        Optional<User> result = queryUserRepository.queryByEmail(email);
        
        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getId());
        assertEquals(user.getUsername(), result.get().getUsername());
        assertEquals(user.getEmail(), result.get().getEmail());
    }


}
