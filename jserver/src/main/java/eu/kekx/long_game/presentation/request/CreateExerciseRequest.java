package eu.kekx.long_game.presentation.request;

import eu.kekx.long_game.domain.workout.MetricType;

import java.util.List;

public record CreateExerciseRequest(
    String name,
    String description,
    String link,
    List<MetricDefinitionRequest> metricDefinitions
) {}
