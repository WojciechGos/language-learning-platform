package platform.backend.Work.Lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import platform.backend.Work.Excercise.Exercise;
import platform.backend.Work.Excercise.ExerciseService;
import platform.backend.Work.Task.Task;
import platform.backend.claude.Integration.ClaudeMessageResponse;
import platform.backend.claude.Integration.ClaudeService;
import platform.backend.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ExerciseService exerciseService;
    private final ClaudeService claudeService;

    public List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).get();
    }

    public Lesson addLesson(LessonRequest lessonRequest) {
        Lesson lesson = new Lesson(lessonRequest.name(), "", new ArrayList<>());
        return lessonRepository.save(lesson);
    }

    public Lesson addTaskToLesson(Task task, Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(()-> new ResourceNotFoundException("Lesson with id [%s] not found".formatted(lessonId)));
        lesson.getTaskList().add(task);
        return lessonRepository.save(lesson);
    }

    public Lesson submitLesson(Long lessonId){
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(()-> new ResourceNotFoundException("Lesson with id [%s] not found".formatted(lessonId)));
        lesson.setState(LessonState.COMPLETED);

        System.out.println(lesson.toString());

        int sum = 0;
        int maxPoints = 0;
        for(Task task : lesson.getTaskList()){
            for(Exercise exercise : task.getExcerciseList()){
                sum += exercise.getPoints();
                maxPoints += 1;
            }
        }


        lesson.setTotalPoints(sum);
        lesson.setMaxPoints(maxPoints);
        lesson.setState(LessonState.COMPLETED);


        ClaudeMessageResponse response = claudeService.getClaudeMessageResponse(
                lesson.toString() + "in <type>  tags are exercise type.\n" +
                        "in <points> tags are points that user score, typically 1 point mean user did a good job.\n" +
                        "in <totalPoints> are all points that user scored.\n" +
                        "in <maxPoints> are all possible points.\n",
                "Your task is to generate a personalized motivational feedback like owca wk from youtube channel \"warszawski koks\" on the given xml input. Address specific needs and offer encouragement, support, and guidance. Employ a positive, empathetic, and inspiring tone to help the user feel motivated and empowered for english learning. Use relevant examples, analogies, or quotes to reinforce your message and make it more impactful. Ensure that the message is concise, authentic, and easy to understand. User described tags like type, points, totalPoints, maxPoints to scoupe out of the User. Translate it to the polish. DO NOT USE vulgar words like \"zapierdzielasz\", \"zapierdalać\", \"kurwa\", \"pierdol\", \"jaja\", also do not use icons."
        );
        System.out.println(response.content().get(0).text());
        lesson.setFeedback(response.content().get(0).text());

        lessonRepository.save(lesson);

        return lesson;
    }

}
