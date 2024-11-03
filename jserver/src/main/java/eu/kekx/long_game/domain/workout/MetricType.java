package eu.kekx.long_game.domain.workout;

public enum MetricType {
    WEIGHT(MetricValueType.NUMBER),
    REPS(MetricValueType.NUMBER),
    SETS(MetricValueType.NUMBER),
    DISTANCE(MetricValueType.NUMBER),
    SPEED(MetricValueType.NUMBER),
    TIME(MetricValueType.NUMBER),
    BODY_WEIGHT(MetricValueType.NUMBER),
    ADDITIONAL_WEIGHT(MetricValueType.NUMBER);
    
    private final MetricValueType valueType;
    
    MetricType(MetricValueType valueType) {
        this.valueType = valueType;
    }
    
    public static MetricValueType getValueType(MetricType type) {
        return type.valueType;
    }
}
