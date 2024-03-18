package platform.backend.Work.Lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import platform.backend.Work.Excercise.ExerciseService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ExerciseService exerciseService;

    public List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }

    public Lesson addLesson(Lesson lesson) {

        return lessonRepository.save(lesson);
    }

}
