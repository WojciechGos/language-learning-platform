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
    @OneToMany
    private List<Task> taskList;
}
