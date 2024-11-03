package eu.kekx.long_game.presentation.request;

import java.util.List;

public record CreateExerciseRequest(
    String name,
    String description,
    String link,
    List<MetricDefinitionRequest> metricDefinitions
) {}
