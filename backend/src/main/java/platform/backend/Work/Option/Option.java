package platform.backend.Work.Option;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import platform.backend.Work.Task.Task;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Option {
    @SequenceGenerator(
            name = "option_sequence",
            sequenceName = "option_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "option_sequence"
    )
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Option(String content) {
        this.content = content;
    }

}
