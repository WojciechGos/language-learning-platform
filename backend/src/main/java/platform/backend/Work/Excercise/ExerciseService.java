package platform.backend.Work.Excercise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import platform.backend.claude.functions.definition.ClaudeExerciseResponse;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public Exercise addExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }



    public List<Exercise> getExerciseList(List<ClaudeExerciseResponse> claudeExerciseResponsesList) {
        List<Exercise> exerciseList = new ArrayList<>();

        for(ClaudeExerciseResponse claudeExerciseResponse : claudeExerciseResponsesList){
            exerciseList.add(new Exercise(claudeExerciseResponse.content(), claudeExerciseResponse.answer()));
        }

        return exerciseRepository.saveAll(exerciseList);
    }

    public Exercise updateExercise(ExerciseRequest exerciseRequest, Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> new ResourceAccessException("Exercise with id [%s] not found".formatted(exerciseId)));

        exercise.setUserAnswer(exerciseRequest.userAnswer());

        return exerciseRepository.save(exercise);
    }

}
