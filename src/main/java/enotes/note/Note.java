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

@Getter @Setter
@EqualsAndHashCode(of = {"header", "body", "comments", "state", "user"})
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Note implements Serializable {
    private Long id;
    private String header;
    private String body;

    @Singular
    private List<Comment> comments;
    private NoteState state;
    private User user;
    private boolean isDeleted;
}
