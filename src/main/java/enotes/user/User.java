package enotes.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import enotes.note.Note;

import enotes.user.role.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter @Setter
@EqualsAndHashCode(of = {"firstName", "lastName", "email"})
@NoArgsConstructor
@ToString
public class User implements Serializable {
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;

    private String password;
    @NonNull
    private int age;
    @NonNull
    private String country;
    @NonNull
    private Date registration;
    @NonNull
    private UserRole role;

    @Singular
    private List<Note> notes;
}
