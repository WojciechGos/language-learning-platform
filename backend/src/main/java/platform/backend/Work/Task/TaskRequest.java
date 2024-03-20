package platform.backend.Work.Task;

public record TaskRequest(
        String topic,
        String type,
        String answer,
        Long lessonId
) {
}
