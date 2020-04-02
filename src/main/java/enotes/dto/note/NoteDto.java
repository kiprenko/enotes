package enotes.dto.note;

import enotes.entity.note.notestate.NoteState;
import enotes.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter @Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteDto implements Serializable {

    private Long id;
    private Integer version;
    private String header;
    private String body;
    private NoteState state;
    private User user;
    private boolean isDeleted;
}
