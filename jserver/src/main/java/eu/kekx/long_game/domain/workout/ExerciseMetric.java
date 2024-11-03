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
@Table(name = "exercise_metrics")
public class ExerciseMetric extends AbstractEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_exercise_id", nullable = false)
    private WorkoutExercise workoutExercise;
    
    @Column(name = "metric_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MetricType builtInType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custom_metric_type_id")
    private CustomMetricType customType;
    
    @Column(name = "number_value")
    private Double numberValue;
    
    @Column(name = "boolean_value")
    private Boolean booleanValue;
    
    @Column(name = "unit")
    private String unit;
    
    @PrePersist
    @PreUpdate
    private void validateValue() {
        if ((numberValue == null && booleanValue == null) || (numberValue != null && booleanValue != null)) {
            throw new IllegalStateException("Exercise metric must have exactly one value type set");
        }
        
        MetricValueType valueType = customType != null ? 
            customType.getValueType() : 
            MetricType.getValueType(builtInType);
            
        switch (valueType) {
            case NUMBER -> {
                if (numberValue == null) {
                    throw new IllegalStateException("Number value required for metric type: " + 
                        (customType != null ? customType.getName() : builtInType));
                }
            }
            case BOOLEAN -> {
                if (booleanValue == null) {
                    throw new IllegalStateException("Boolean value required for metric type: " + 
                        (customType != null ? customType.getName() : builtInType));
                }
            }
        }
    }
}
