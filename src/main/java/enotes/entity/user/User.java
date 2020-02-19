package enotes.entity.user;

import enotes.entity.note.Note;
import enotes.entity.userrole.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinColumn(name = "role_id")
    private UserRole role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @Singular
    private List<Note> notes = new ArrayList<>();
}
