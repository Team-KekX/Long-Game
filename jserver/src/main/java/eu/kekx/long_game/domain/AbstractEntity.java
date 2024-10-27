package eu.kekx.long_game.domain;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @EqualsAndHashCode.Exclude
    private Integer version = 0;
}
