package enotes.dto.note;

import enotes.dto.user.UserDto;
import enotes.data.note.NoteState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

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
    private UserDto user;
    private boolean isDone;
    private LocalDate created;
    private LocalDate lastModified;
    private LocalDate archivedAt;
    private boolean isDeleted;
    private boolean isArchived;
}
