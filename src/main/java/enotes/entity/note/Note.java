package enotes.entity.note;

import enotes.entity.note.notestate.NoteState;
import enotes.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "notes")
@Getter @Setter
@EqualsAndHashCode(of = {"header", "body", "state", "user", "version"})
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"header", "body", "state", "user", "version"})
@Builder
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

    @NotNull
    private String header;
    private String body;
    @NotNull
    @Enumerated(EnumType.STRING)
    private NoteState state;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private boolean isDeleted;
}
