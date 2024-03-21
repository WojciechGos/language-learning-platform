package platform.backend.Work.Task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import platform.backend.Work.Excercise.Exercise;
import platform.backend.Work.Excercise.ExerciseService;
import platform.backend.Work.Lesson.LessonService;
import platform.backend.claude.Integration.ClaudeMessageResponse;
import platform.backend.claude.Integration.ClaudeService;
import platform.backend.claude.functions.definition.exercise.*;
import platform.backend.claude.functions.definition.exercise.audio.AudioExercise;
import platform.backend.claude.functions.definition.exercise.audio.ClaudeAudioExerciseResponse;
import platform.backend.ibm.IBMService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ClaudeService claudeService;
    private final ExerciseService exerciseService;
    private final LessonService lessonService;
    private final IBMService ibmService;

    public Task generateTask(TaskRequest request) {
        List<ClaudeExerciseResponse> claudeExerciseResponsesList = new ArrayList<>();
        String description = "";
        ExerciseDefinition exerciseDefinition = null;

        if(request.type().equals("antonym")){
            exerciseDefinition = new AntonymExercise(request.topic());
            description = "group related antonyms";

        }
        else if(request.type().equals("past simple past continuous")) {
            exerciseDefinition = new PastSPastCExercise(request.topic(), "B2");
            description = "Write the word in parentheses in the appropriate simple past or past continuous tense.";
        }
        else if(request.type().equals("scatter")) {
            exerciseDefinition = new ScatterExercise(request.topic());
            description = "form words from scattered letters.";
        }
        else if(request.type().equals("prefix suffix")){
            exerciseDefinition = new PrefixSuffixExercise(request.topic());
            description = "Add appriopriate prefix or suffix to the word.";

        }
        else if(request.type().equals("third conditional")){
            exerciseDefinition = new ThirdConditionalExercise(request.topic());
            description = "Rewrite these sentences, using third conditional.";
        }
        else if(request.type().equals("second conditional")){
            exerciseDefinition = new SecondConditionalExercise(request.topic());
            description = "Rewrite these sentences, using second conditional.";
        }
        else if(request.type().equals("first conditional")){
            exerciseDefinition = new FirstConditionalExercise(request.topic());
            description = "Rewrite these sentences, using first conditional.";
        }
        else{
            throw new IllegalStateException("Invalid exercise type");
        }
        
        ClaudeMessageResponse claudeMessageResponse = claudeService.getClaudeMessageResponse(
                exerciseDefinition.getSystemPrompt(),
                exerciseDefinition.getUserPrompt()
        );

        System.out.println(claudeMessageResponse);
        System.out.println(exerciseDefinition.getSystemPrompt());
        claudeExerciseResponsesList = exerciseDefinition.getResponseData(claudeMessageResponse.content().get(0).text());

        List<Exercise> exerciseList = exerciseService.getExerciseList(claudeExerciseResponsesList);
        Task task = taskRepository.save(new Task(description, request.type(), exerciseList));

        lessonService.addTaskToLesson(task, request.lessonId());
        return task;
    }

    public Task generateAudioTask(TaskRequest request) {
        String description = "Listen to the audio and answer the question";
        ExerciseDefinition exerciseDefinition = new AudioExercise(request.topic());

        ClaudeMessageResponse claudeMessageResponse = claudeService.getClaudeMessageResponse(
                exerciseDefinition.getSystemPrompt(),
                exerciseDefinition.getUserPrompt()
        );

        System.out.println(claudeMessageResponse);
        System.out.println(exerciseDefinition.getSystemPrompt());
        ClaudeAudioExerciseResponse claudeExerciseResponsesList = ((AudioExercise) exerciseDefinition).getAudioResponse(claudeMessageResponse.content().get(0).text());


        String audioText = claudeExerciseResponsesList.audio();

        byte[] audio = ibmService.convertTextToSpeech(audioText);


        List<ClaudeExerciseResponse> exerciseResponseList = claudeExerciseResponsesList.claudeExerciseResponseList();


        List<Exercise> exerciseList = exerciseService.getExerciseList(exerciseResponseList);
        Task task = new Task(description + "\n" +
                claudeExerciseResponsesList.audio(),
                "audio", exerciseList);

        task.setAudio(audio);

        taskRepository.save(task);

        lessonService.addTaskToLesson(task, request.lessonId());
        return task;
    }

}
