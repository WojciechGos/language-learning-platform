package platform.backend.Work.Task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import platform.backend.Work.Excercise.Exercise;
import platform.backend.Work.Excercise.ExerciseService;
import platform.backend.Work.Lesson.LessonService;
import platform.backend.claude.Integration.ClaudeMessageResponse;
import platform.backend.claude.Integration.ClaudeService;
import platform.backend.claude.functions.definition.exercise.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ClaudeService claudeService;
    private final ExerciseService exerciseService;
    private final LessonService lessonService;

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
            description = "form words from scattered letters.";

        }
        else if(request.type().equals("third conditional")){
            exerciseDefinition = new ThirdConditionalExercise(request.topic());
            description = "form words from scattered letters.";
        }else{
            throw new IllegalStateException("Invalid exercise type");
        }
        
        ClaudeMessageResponse claudeMessageResponse = claudeService.getClaudeMessageResponse(
                exerciseDefinition.getSystemPrompt(),
                exerciseDefinition.getUserPrompt()
        );

        System.out.println(claudeMessageResponse);
        claudeExerciseResponsesList = exerciseDefinition.getResponseData(claudeMessageResponse.content().get(0).text());

        List<Exercise> exerciseList = exerciseService.getExerciseList(claudeExerciseResponsesList);
        Task task = taskRepository.save(new Task(description, request.type(), exerciseList));

        lessonService.addTaskToLesson(task, request.lessonId());
        return task;
    }
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("Task with id " + id + " does not exist"));
    }



    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
