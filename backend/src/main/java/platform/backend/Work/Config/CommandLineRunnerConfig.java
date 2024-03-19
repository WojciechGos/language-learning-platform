package platform.backend.Work.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import platform.backend.Work.Excercise.Exercise;
import platform.backend.Work.Excercise.ExerciseRepository;
import platform.backend.Work.Excercise.ExerciseService;
import platform.backend.Work.Lesson.Lesson;
import platform.backend.Work.Lesson.LessonRepository;
import platform.backend.Work.Lesson.LessonService;
import platform.backend.Work.Lesson.LessonState;

import platform.backend.Work.Task.Task;
import platform.backend.Work.Task.TaskRepository;
import platform.backend.Work.Task.TaskService;

import java.util.List;
@Configuration
public class CommandLineRunnerConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            LessonRepository lessonRepository,
            TaskRepository taskRepository,
            ExerciseRepository exerciseRepository
    ) {
        return args -> {
            populate(lessonRepository, taskRepository, exerciseRepository);
        };
    }

    private void populate(
            LessonRepository lessonRepository,
            TaskRepository taskRepository,
            ExerciseRepository exerciseRepository
    ) {
        String grammarAnswer1 = "Inflatable 35 cm wheels provide a comfortable ride, allowing drivers to travel across rough, open ground.";
        String grammarAnswer2 = "The operating range of 60 km allows drivers to return, without needing to recharge the batteries.";


        Exercise grammarExercise1 = new Exercise("Inflatable 35 cm wheels provide a comfortable ride. They allow drivers to travel across rough, open ground.", grammarAnswer1);
        Exercise grammarExercise2 = new Exercise("The operating range of 60 km allows drivers to return. They do not need to recharge the batteries. (without)", grammarAnswer2);
        List<Exercise> grammarExercisesList1 = List.of(
                grammarExercise1,
                grammarExercise2
        );


//        List<Exercise> exerciseList = exerciseRepository.findAll();

        Task grammarTask = new Task(
                "Join these pairs of sentences into single sentences, using a present participle phrase (verb+ing). Some sentences require the additional word in brackets to be used.",
                grammarExercisesList1
        );
        taskRepository.saveAll(List.of(grammarTask));
        exerciseRepository.saveAll(grammarExercisesList1);

        Lesson lesson = new Lesson("spin offs", "spin offs", List.of(grammarTask));
        lessonRepository.save(lesson);
    }
}