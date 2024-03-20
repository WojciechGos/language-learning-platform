package platform.backend.Work.Lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import platform.backend.Work.Excercise.ExerciseService;
import platform.backend.Work.Task.Task;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ExerciseService exerciseService;

    public List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).get();
    }

    public Lesson addLesson(LessonRequest lessonRequest) {
        Lesson lesson = new Lesson(lessonRequest.name());
        return lessonRepository.save(lesson);
    }

    public Lesson addTaskToLesson(Task task, Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        lesson.getTaskList().add(task);
        return lessonRepository.save(lesson);

    }

}
