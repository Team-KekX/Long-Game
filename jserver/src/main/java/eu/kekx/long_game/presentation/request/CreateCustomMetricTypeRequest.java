package eu.kekx.long_game.presentation.request;

public record CreateCustomMetricTypeRequest(
    String name,
    String description,
    String defaultUnit
) {}
