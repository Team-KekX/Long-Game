package eu.kekx.long_game.domain.workout;

import eu.kekx.long_game.domain.AbstractEntity;
import eu.kekx.long_game.domain.User;
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
@Table(name = "custom_metric_types")
public class CustomMetricType extends AbstractEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "default_unit")
    private String defaultUnit;
    
    @Column(name = "value_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MetricValueType valueType = MetricValueType.NUMBER;
}
