package enotes.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import enotes.note.Note;

import enotes.user.role.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter @Setter
@EqualsAndHashCode(of = {"firstName", "lastName", "email", "age", "country", "registration", "role"})
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int age;
    private String country;
    private Date registration;
    private UserRole role;

    @Singular
    private List<Note> notes;
}
