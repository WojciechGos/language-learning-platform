package platform.backend.Work.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import platform.backend.Work.Excercise.Exercise;
import platform.backend.Work.Excercise.ExerciseRepository;
import platform.backend.Work.Excercise.ExerciseService;
import platform.backend.Work.Excercise.types.GrammarExercise;
import platform.backend.Work.Lesson.Lesson;
import platform.backend.Work.Lesson.LessonRepository;
import platform.backend.Work.Lesson.LessonService;
import platform.backend.Work.Lesson.LessonState;
import platform.backend.Work.Option.Option;
import platform.backend.Work.Option.OptionRepository;
import platform.backend.Work.Option.OptionService;
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
            OptionRepository optionRepository,
            ExerciseRepository exerciseRepository
    ) {
        return args -> {
            populate(lessonRepository, taskRepository, optionRepository, exerciseRepository);
        };
    }

    private void populate(
            LessonRepository lessonRepository,
            TaskRepository taskRepository,
            OptionRepository optionRepository,
            ExerciseRepository exerciseRepository
    ) {

        Option grammarAnswer1 = new Option("Inflatable 35 cm wheels provide a comfortable ride, allowing drivers to travel across rough, open ground.");
        Option grammarAnswer2 = new Option("The operating range of 60 km allows drivers to return, without needing to recharge the batteries.");

        List<Option> grammarAnswersList1 = List.of(
                grammarAnswer1,
                grammarAnswer2
        );

        optionRepository.saveAll(grammarAnswersList1);

        List<Option> optionList = optionRepository.findAll();

        GrammarExercise grammarExercise1 = new GrammarExercise("Inflatable 35 cm wheels provide a comfortable ride. They allow drivers to travel across rough, open ground.",optionList.get(0));
        GrammarExercise grammarExercise2 = new GrammarExercise("The operating range of 60 km allows drivers to return. They do not need to recharge the batteries. (without)", optionList.get(1));

        List<Exercise> grammarExercisesList1 = List.of(
                grammarExercise1,
                grammarExercise2
        );

        exerciseRepository.saveAll(grammarExercisesList1);

        List<Exercise> exerciseList = exerciseRepository.findAll();

        Task grammarTask = new Task(
                "Join these pairs of sentences into single sentences, using a present participle phrase (verb+ing). Some sentences require the additional word in brackets to be used.",
                exerciseList,
                optionList
        );

        taskRepository.saveAll(List.of(grammarTask));

        Lesson lesson = new Lesson("spin offs", "spin offs",List.of(grammarTask));
        lessonRepository.save(lesson);
    }
}