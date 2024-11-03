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
    
    @Column(name = "metric_type")
    @Enumerated(EnumType.STRING)
    private MetricType builtInType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custom_metric_type_id")
    private CustomMetricType customType;
    
    @Column(name = "required", nullable = false)
    private boolean required = false;
    
    @Column(name = "unit")
    private String unit;
    
    @PrePersist
    @PreUpdate
    private void validateMetricType() {
        if ((builtInType == null && customType == null) || (builtInType != null && customType != null)) {
            throw new IllegalStateException("Exercise metric must have either a built-in type or a custom type, but not both");
        }
    }
}
