package enotes.data.note;

import enotes.data.user.User;
import enotes.dto.note.NoteDto;

import java.util.List;

public interface NoteManager {
    void saveNew(NoteDto noteDto, User user);

    List<NoteDto> getAll(User user);
}
