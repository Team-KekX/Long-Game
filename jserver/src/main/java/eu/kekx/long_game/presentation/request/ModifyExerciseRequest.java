package eu.kekx.long_game.presentation.request;

import java.util.Optional;

public record ModifyExerciseRequest(
    Optional<String> name,
    Optional<String> description,
    Optional<String> link
) {}
