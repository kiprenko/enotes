package enotes.dto.comment;

import enotes.dto.note.NoteDto;
import enotes.dto.user.UserDto;
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
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class CommentDto implements Serializable {

    private Long id;
    private Integer version;
    private String text;
    private UserDto user;
    private NoteDto note;
}
