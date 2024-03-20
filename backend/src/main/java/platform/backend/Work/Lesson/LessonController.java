package platform.backend.Work.Lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable("id") Long id) {
        Lesson lesson = lessonService.getLessonById(id);
        return new ResponseEntity<>(lesson, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Lesson> addLesson(@RequestBody LessonRequest lesson) {
        Lesson newLesson = lessonService.addLesson(lesson);
        return new ResponseEntity<>(newLesson, HttpStatus.CREATED);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Lesson> addFeedback(@PathVariable("id") Long id) {
        Lesson feedback = lessonService.submitLesson(id);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }
}


