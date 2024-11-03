package eu.kekx.long_game.presentation.request;

public record ModifyExerciseRequest(
    Long exerciseId,
    String name,
    String description,
    String link
) {}
