package enotes.entity.user;

import enotes.entity.note.Note;
import enotes.entity.user.role.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
@EqualsAndHashCode(of = {"firstName", "lastName", "email", "age", "country", "registration", "role", "version"})
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int age;
    private String country;
    private Date registration;
    @ManyToOne
    private UserRole role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users", orphanRemoval = true)
    @Singular
    private List<Note> notes = new ArrayList<>();
}
