package enotes.comment;

import java.io.Serializable;

import enotes.note.Note;
import enotes.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@EqualsAndHashCode(of = {"text", "user", "note"})
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Comment implements Serializable {
    private Long id;
    private String text;
    private User user;
    private Note note;
}
