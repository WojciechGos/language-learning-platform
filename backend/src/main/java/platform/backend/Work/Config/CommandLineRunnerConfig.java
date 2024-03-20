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

import java.util.ArrayList;
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

        Task grammarTask = new Task(
                "Join these pairs of sentences into single sentences, using a present participle phrase (verb+ing). Some sentences require the additional word in brackets to be used.",
                "grammar",
                grammarExercisesList1
        );
        taskRepository.saveAll(List.of(grammarTask));
        exerciseRepository.saveAll(grammarExercisesList1);
        /////////////////////////////////////////////// ////////////////////////////


        Exercise pastSpastCExercise1 = new Exercise("While the Avengers _____ (fight) Thanos, Iron Man _____ (try) to figure out a way to defeat him once and for all.", "were fighting, was trying");
        Exercise pastSpastCExercise2 = new Exercise("As Black Widow _____ (infiltrate) the enemy base, Hawkeye _____ (provide) cover from a nearby rooftop.", "was infiltrating, was providing");
        Exercise pastSpastCExercise3 = new Exercise("Thor _____ (summon) lightning to attack the Chitauri army while Captain America _____ (lead) the ground assault.", "summoned, was leading");
        Exercise pastSpastCExercise4 = new Exercise("The Hulk _____ (smash) through the alien invaders as Scarlet Witch _____ (use) her powers to protect civilians.", "was smashing, was using");
        Exercise pastSpastCExercise5 = new Exercise("When Ant-Man _____ (shrink) down to infiltrate Ultron's systems, Vision _____ (analyze) the robot's weaknesses.", "shrank, was analyzing");




        List<Exercise> pastSpastCExercisesList1 = List.of(
                pastSpastCExercise1,
                pastSpastCExercise2,
                pastSpastCExercise3,
                pastSpastCExercise4,
                pastSpastCExercise5
        );

        Task pastSimplePastContinuousTask = new Task(
                "Write the word in parentheses in the appropriate simple past or past continuous tense.",
                "past simple past continuous",
                pastSpastCExercisesList1
        );

        taskRepository.save(pastSimplePastContinuousTask);
        exerciseRepository.saveAll(pastSpastCExercisesList1);

        Lesson lesson = new Lesson("spin offs", List.of(grammarTask, pastSimplePastContinuousTask),4,7);
        lessonRepository.save(lesson);




    }
}