package eu.kekx.long_game.persistence.workout;

import eu.kekx.long_game.domain.workout.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    boolean existsByName(String name);
}
