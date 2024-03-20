package platform.backend.Work.Task;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import platform.backend.Work.Excercise.Exercise;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Task {
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private Long id;
    private String description;
    private String type;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Exercise> excerciseList;


    public Task(String description, List<Exercise> excerciseList) {
        this.description = description;
        this.excerciseList = excerciseList;
    }

    public Task(String description, String type, List<Exercise> excerciseList) {
        this.description = description;
        this.type = type;
        this.excerciseList = excerciseList;
    }

    @Override
    public String toString() {
        return "<task>" +
                "<type>" + type + "</type>" +
                "<description>" + description + "</description>"+
                "<excerciseList>" + excerciseList + "</exerciseList>" +
                "</task>";
    }
}
