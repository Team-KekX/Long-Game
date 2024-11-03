package eu.kekx.long_game.persistence.workout;

import eu.kekx.long_game.domain.User;
import eu.kekx.long_game.domain.workout.CustomMetricType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomMetricTypeRepository extends JpaRepository<CustomMetricType, Long> {
    
    boolean existsByUserAndName(User user, String name);
    
    Optional<CustomMetricType> findByUserAndName(User user, String name);
    
    List<CustomMetricType> findAllByUser(User user);
    
    void deleteByUserAndName(User user, String name);
}
