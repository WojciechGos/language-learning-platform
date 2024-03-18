package platform.backend.Work.Lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/lessons")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<Lesson>> getLessons() {
        List<Lesson> lessons = lessonService.getLessons();
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    @PostMapping ResponseEntity<Lesson> addLesson(Lesson lesson) {
        Lesson newLesson = lessonService.addLesson(lesson);
        return new ResponseEntity<>(newLesson, HttpStatus.CREATED);
    }
}


