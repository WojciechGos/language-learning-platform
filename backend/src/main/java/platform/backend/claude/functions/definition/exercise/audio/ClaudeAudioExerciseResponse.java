package platform.backend.claude.functions.definition.exercise.audio;

import platform.backend.claude.functions.definition.exercise.ClaudeExerciseResponse;

import java.util.List;

public record ClaudeAudioExerciseResponse(
        String audio,
        List<ClaudeExerciseResponse> claudeExerciseResponseList
) {
}
