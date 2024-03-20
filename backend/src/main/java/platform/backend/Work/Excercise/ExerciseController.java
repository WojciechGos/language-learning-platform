package platform.backend.Work.Excercise;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PatchMapping("{id}")
    public ResponseEntity<Exercise> updateExercise(@RequestBody ExerciseRequest exerciseRequest, @PathVariable("id") Long exerciseId) {
        Exercise exercise = exerciseService.updateExercise(exerciseRequest, exerciseId);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }
}
