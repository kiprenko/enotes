package enotes.comment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import enotes.note.Note;
import enotes.user.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@EqualsAndHashCode(of = {"text", "user", "note"})
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Comment implements Serializable {
    private Long id;
    @NonNull
    private String text;
    @NonNull
    private User user;
    @NotNull
    private Note note;
}
