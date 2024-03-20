package platform.backend.Work.Excercise;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Exercise {
    @SequenceGenerator(
            name = "exercise_sequence",
            sequenceName = "exercise_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "exercise_sequence"
    )
    private Long id;
    private int points = 0;
    private String content;
    private String userAnswer;
    private String correctAnswer;


    public Exercise(String content, String correctAnswer) {
        this.content = content;
        this.correctAnswer = correctAnswer;

    }

    @Override
    public String toString() {
        return "<exercise>" +
                "<points>" + points + "</points>" +
                "<content>" + content + "</content>" +
                "<userAnswer>" + userAnswer + "</userAnswer>" +
                "<correctAnswer>'" + correctAnswer + "</correctAnswer>" +
                "</exercise>";
    }
}
