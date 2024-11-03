package eu.kekx.long_game.persistence.workout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import eu.kekx.long_game.domain.User;
import eu.kekx.long_game.domain.workout.CustomMetricType;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CustomMetricTypeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomMetricTypeRepository customMetricTypeRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        // Create a test user first
        testUser = new User();
        testUser.setUsername("testUser");
        testUser.setEmail("test@example.com");
        testUser.setPassword("password");
        testUser = entityManager.persist(testUser);

        // Create some sample custom metric types
        for (int i = 1; i <= 3; i++) {
            CustomMetricType metricType = new CustomMetricType();
            metricType.setUser(testUser);
            metricType.setName("CustomMetric" + i);
            metricType.setDescription("Description" + i);
            metricType.setDefaultUnit("Unit" + i);
            entityManager.persist(metricType);
        }
        entityManager.flush();
    }

    @Test
    void existsByUserAndName_WhenExists_ReturnsTrue() {
        boolean exists = customMetricTypeRepository.existsByUserAndName(testUser, "CustomMetric1");
        assertTrue(exists);
    }

    @Test
    void existsByUserAndName_WhenDoesNotExist_ReturnsFalse() {
        boolean exists = customMetricTypeRepository.existsByUserAndName(testUser, "NonExistentMetric");
        assertFalse(exists);
    }

    @Test
    void findByUserAndName_WhenExists_ReturnsMetricType() {
        Optional<CustomMetricType> result = customMetricTypeRepository.findByUserAndName(testUser, "CustomMetric1");
        
        assertTrue(result.isPresent());
        assertEquals("CustomMetric1", result.get().getName());
        assertEquals(testUser.getId(), result.get().getUser().getId());
    }

    @Test
    void findByUserAndName_WhenDoesNotExist_ReturnsEmptyOptional() {
        Optional<CustomMetricType> result = customMetricTypeRepository.findByUserAndName(testUser, "NonExistentMetric");
        assertTrue(result.isEmpty());
    }

    @Test
    void findAllByUser_ReturnsAllUserMetrics() {
        List<CustomMetricType> results = customMetricTypeRepository.findAllByUser(testUser);
        
        assertEquals(3, results.size());
        assertTrue(results.stream().allMatch(mt -> mt.getUser().getId().equals(testUser.getId())));
    }

    @Test
    void deleteByUserAndName_DeletesMetric() {
        customMetricTypeRepository.deleteByUserAndName(testUser, "CustomMetric1");
        entityManager.flush();
        
        Optional<CustomMetricType> result = customMetricTypeRepository.findByUserAndName(testUser, "CustomMetric1");
        assertTrue(result.isEmpty());
    }
}
