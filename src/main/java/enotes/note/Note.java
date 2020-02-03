package enotes.note;

import enotes.note.noteState.NoteState;
import enotes.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "notes")
@Getter @Setter
@EqualsAndHashCode(of = {"header", "body", "state", "user"})
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @NotNull
    private String header;
    private String body;
    @NotNull
    private NoteState state;
    @NotNull
    private User user;
    private boolean isDeleted;
}
