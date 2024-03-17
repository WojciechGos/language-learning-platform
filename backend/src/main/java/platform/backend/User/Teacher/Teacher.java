package platform.backend.User.Teacher;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import platform.backend.User.User.User;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Teacher extends User {
}
