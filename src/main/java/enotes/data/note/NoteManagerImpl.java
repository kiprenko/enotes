package enotes.data.note;

import enotes.data.user.User;
import enotes.dto.note.NoteDto;
import enotes.dto.note.NoteDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoteManagerImpl implements NoteManager {

    private final NoteDtoConverter noteDtoConverter;
    private final NoteService noteService;

    @Autowired
    public NoteManagerImpl(NoteDtoConverter noteDtoConverter, NoteService noteService) {
        this.noteDtoConverter = noteDtoConverter;
        this.noteService = noteService;
    }

    @Override
    public void saveNew(NoteDto noteDto, User user) {
        Note noteEntity = noteDtoConverter.convertToEntitySkipNull(noteDto);
        noteEntity.setUser(user);
        noteEntity.setCreated(LocalDate.now());
        noteService.save(noteEntity);
    }

    @Override
    public List<NoteDto> getAll(User user) {
        List<Note> notes = noteService.getAllNotes(user);
        return notes.stream().map(noteDtoConverter::convertToDtoSkipNull).collect(Collectors.toList());
    }
}
