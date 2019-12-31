package enotes.comment;

import java.io.Serializable;

import enotes.note.Note;
import enotes.user.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@EqualsAndHashCode(of = {"text", "user", "note"})
@NoArgsConstructor
@ToString
public class Comment implements Serializable {
    private Long id;
    private String text;
    private User user;
    private Note note;
}
