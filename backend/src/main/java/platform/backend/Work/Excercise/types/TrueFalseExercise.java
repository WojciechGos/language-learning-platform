package platform.backend.Work.Excercise.types;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import platform.backend.Work.Excercise.Exercise;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class TrueFalseExercise extends Exercise {
    private String content;
    private boolean answer;
}
