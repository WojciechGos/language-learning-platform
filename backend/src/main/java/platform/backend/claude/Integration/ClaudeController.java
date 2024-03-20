package platform.backend.claude.Integration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.backend.claude.functions.definition.AntonymExercise;
import platform.backend.claude.functions.definition.ClaudeExerciseResponse;
import platform.backend.claude.functions.definition.ExerciseDefinition;
import platform.backend.claude.functions.definition.PastSPastCExercise;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/claude")
public class ClaudeController {
    private final ClaudeService claudeService;

    public ClaudeController(ClaudeService claudeService) {
        this.claudeService = claudeService;
    }

    @PostMapping
    public ResponseEntity<String> getClaudeResponse() {
        ExerciseDefinition anatomyExercise = new AntonymExercise("avengers");

//        System.out.println(anatomyExercise.getSystemPrompt());
//        System.out.println(anatomyExercise.getUserPrompt());

//        ExerciseDefinition pastSimplePastContinuousExercise = new PastSPastCExercise("past simple past Continuous", "B2");
//        System.out.println(pastSimplePastContinuousExercise.getSystemPrompt());
//        System.out.println(pastSimplePastContinuousExercise.getUserPrompt());

            String[]contents= "powerful, strong, brave, courageous, fearless, mighty, invincible, unstoppable, heroic, valiant".split(",");
            String[]answers= "weak, cowardly, vulnerable, stoppable, villainous, timid".split(",");

        List<ClaudeExerciseResponse> claudeExerciseResponsesList = new ArrayList<>();
        System.out.println("contents: " + contents.length);
        System.out.println("answers: " + answers.length);
        for(int i = 0; i < contents.length; i++){
            System.out.println("Content: " + contents[i]);
            System.out.println(i);
            if( i <= (answers.length-1))
                claudeExerciseResponsesList.add(new ClaudeExerciseResponse(contents[i], answers[i]));
        }

//        ClaudeMessageResponse response = claudeService.getClaudeMessageResponse(anatomyExercise.getSystemPrompt(), anatomyExercise.getUserPrompt());
//        System.out.println(response);

        return new ResponseEntity<>("response", HttpStatus.OK);
    }

}
