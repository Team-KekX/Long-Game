package eu.kekx.long_game.service.domain;

import eu.kekx.long_game.domain.workout.Exercise;
import eu.kekx.long_game.domain.workout.ExerciseMetricDefinition;
import eu.kekx.long_game.persistence.workout.ExerciseRepository;
import eu.kekx.long_game.presentation.request.CreateExerciseRequest;
import eu.kekx.long_game.presentation.request.MetricDefinitionRequest;
import eu.kekx.long_game.presentation.request.ModifyExerciseRequest;
import eu.kekx.long_game.service.exceptions.NotFoundException;
import eu.kekx.long_game.service.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ExerciseService {
    
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public Exercise createExercise(CreateExerciseRequest dto) {
        Objects.requireNonNull(dto, "CreateExerciseRequest must not be null");
        DtoUtils.checkAllNullsAndBlanks(dto);
        
        var exercise = new Exercise();
        exercise.setName(dto.name());
        exercise.setDescription(dto.description());
        exercise.setLink(dto.link());
        
        for (MetricDefinitionRequest metricDto : dto.metricDefinitions()) {
            var metricDefinition = new ExerciseMetricDefinition();
            metricDefinition.setExercise(exercise);
            metricDefinition.setType(metricDto.type());
            metricDefinition.setRequired(metricDto.required());
            metricDefinition.setUnit(metricDto.unit());
            
            exercise.getMetricDefinitions().add(metricDefinition);
        }
        
        return exerciseRepository.save(exercise);
    }
    
    @Transactional
    public Exercise modifyExercise(Long exerciseId, ModifyExerciseRequest dto) {
        Objects.requireNonNull(dto, "ModifyExerciseRequest must not be null");
        
        var exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NotFoundException("Exercise with id [" + exerciseId + "] not found"));
        
        // Only update fields that are present in the request
        dto.name().ifPresent(name -> {
            if (name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be blank");
            }
            exercise.setName(name);
        });
        
        dto.description().ifPresent(description -> {
            if (description.isBlank()) {
                throw new IllegalArgumentException("Description cannot be blank");
            }
            exercise.setDescription(description);
        });
        
        dto.link().ifPresent(link -> {
            if (link.isBlank()) {
                throw new IllegalArgumentException("Link cannot be blank");
            }
            exercise.setLink(link);
        });
        
        return exerciseRepository.save(exercise);
    }
}
