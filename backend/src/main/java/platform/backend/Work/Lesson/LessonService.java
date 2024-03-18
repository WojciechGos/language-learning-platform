package platform.backend.Work.Lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    public List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }

}
