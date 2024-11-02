package eu.kekx.long_game.presentation.request;

import eu.kekx.long_game.domain.workout.MetricType;

public record MetricDefinitionRequest(
    MetricType type,
    boolean required,
    String unit
) {}
