package eu.kekx.long_game.service.domain;

import eu.kekx.long_game.domain.workout.Exercise;
import eu.kekx.long_game.domain.workout.ExerciseMetricDefinition;
import eu.kekx.long_game.persistence.workout.CustomMetricTypeRepository;
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
    private final CustomMetricTypeRepository customMetricTypeRepository;

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
            
            if (metricDto.builtInType() != null) {
                metricDefinition.setBuiltInType(metricDto.builtInType());
            } else {
                var customType = customMetricTypeRepository.findById(metricDto.customMetricTypeId())
                    .orElseThrow(() -> new NotFoundException("Custom metric type with id [" + 
                        metricDto.customMetricTypeId() + "] not found"));
                metricDefinition.setCustomType(customType);
            }
            
            metricDefinition.setRequired(metricDto.required());
            metricDefinition.setUnit(metricDto.unit());
            
            exercise.getMetricDefinitions().add(metricDefinition);
        }
        
        return exerciseRepository.save(exercise);
    }
    
    @Transactional
    public Exercise modifyExercise(ModifyExerciseRequest dto) {
        Objects.requireNonNull(dto, "ModifyExerciseRequest must not be null");
        Objects.requireNonNull(dto.exerciseId(), "Exercise ID must not be null");
        
        var exercise = exerciseRepository.findById(dto.exerciseId())
                .orElseThrow(() -> new NotFoundException("Exercise with id [" + dto.exerciseId() + "] not found"));
        
        if (dto.name() != null) {
            if (dto.name().isBlank()) {
                throw new IllegalArgumentException("Name cannot be blank");
            }
            exercise.setName(dto.name());
        }
        
        if (dto.description() != null) {
            if (dto.description().isBlank()) {
                throw new IllegalArgumentException("Description cannot be blank");
            }
            exercise.setDescription(dto.description());
        }
        
        if (dto.link() != null) {
            if (dto.link().isBlank()) {
                throw new IllegalArgumentException("Link cannot be blank");
            }
            exercise.setLink(dto.link());
        }
        
        return exerciseRepository.save(exercise);
    }
}
