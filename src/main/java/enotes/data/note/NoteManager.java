package enotes.data.note;

import enotes.dto.note.NoteDto;

public interface NoteManager {
    void save(NoteDto noteDto);

    void getAll();
}
