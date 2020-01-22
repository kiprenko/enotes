package enotes.note;

import enotes.comment.Comment;
import enotes.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "notes")
@Getter @Setter
@EqualsAndHashCode(of = {"header", "body", "comments", "state", "user"})
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String header;
    private String body;

    @Singular
    private List<Comment> comments;
    @NotNull
    private NoteState state;
    @NotNull
    private User user;
    private boolean isDeleted;
}
