package eu.kekx.long_game.domain.workout;

import eu.kekx.long_game.domain.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "exercise_metric_definitions")
public class ExerciseMetricDefinition extends AbstractEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;
    
    @Column(name = "metric_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MetricType type;
    
    @Column(name = "required", nullable = false)
    private boolean required = false;
    
    @Column(name = "unit")
    private String unit;
}
