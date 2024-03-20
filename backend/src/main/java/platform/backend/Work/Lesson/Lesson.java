package platform.backend.Work.Lesson;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import platform.backend.Work.Task.Task;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Lesson {
    @SequenceGenerator(
            name = "lesson_sequence",
            sequenceName = "lesson_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lesson_sequence"
    )
    private Long id;
    private String name;
    private String description;
    private LessonState state = LessonState.PENDING;
    @OneToMany
    private List<Task> taskList;
    private int totalPoints = 0;
    private int maxPoints = 0;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    public Lesson(String name, String description, List<Task> taskList) {
        this.name = name;
        this.taskList = taskList;
    }

    public Lesson(String name) {
        this.name = name;
    }

    public Lesson(String name, List<Task> taskList, int totalPoints, int maxPoints) {
        this.name = name;
        this.taskList = taskList;
        this.totalPoints = totalPoints;
        this.maxPoints = maxPoints;
    }

    @Override
    public String toString() {
        return "<lesson>" +
                "<taskList>" + taskList + "</taskList>" +
                "<totalPoints>" + totalPoints + "</totalPoints>" +
                "<maxPoints>" + maxPoints + "</maxPoints>" +
                "</lesson>";
    }
}
