package platform.backend.Work.Excercise;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import platform.backend.Work.Option.Option;
import platform.backend.Work.Task.Task;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED )
public abstract class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int points = 1;
    private String content;

    @OneToOne
    private Option answer;


    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Exercise(String content, Option answer) {
        this.content = content;
        this.answer = answer;
    }

}
