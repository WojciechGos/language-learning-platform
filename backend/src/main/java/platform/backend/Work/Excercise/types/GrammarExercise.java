package platform.backend.Work.Excercise.types;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import platform.backend.Work.Excercise.Exercise;
import platform.backend.Work.Option.Option;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class GrammarExercise extends Exercise {
    public GrammarExercise(String content, Option answer) {
        super(content, answer);
    }
}
