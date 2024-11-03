package eu.kekx.long_game.presentation.request;

import eu.kekx.long_game.domain.workout.MetricType;

public record MetricDefinitionRequest(
    MetricType builtInType,
    Long customMetricTypeId,
    boolean required,
    String unit
) {
    public MetricDefinitionRequest {
        if ((builtInType == null && customMetricTypeId == null) 
            || (builtInType != null && customMetricTypeId != null)) {
            throw new IllegalArgumentException("Must specify either builtInType or customMetricTypeId, but not both");
        }
    }
}
