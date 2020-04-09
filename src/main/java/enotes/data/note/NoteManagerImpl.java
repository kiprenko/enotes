package enotes.data.note;

import enotes.data.user.User;
import enotes.dto.note.NoteDto;
import enotes.dto.note.NoteDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
        Note noteEntity = noteDtoConverter.convertToEntityIgnoreNull(noteDto);
        noteEntity.setUser(user);
        noteEntity.setCreated(LocalDate.now());
        noteService.save(noteEntity);
    }

    @Override
    public List<NoteDto> getAllUnarchived(User user) {
        List<Note> notes = noteService.getAllUnarchivedNotes(user);
        return notes.stream().map(noteDtoConverter::convertToDtoIgnoreNull).collect(Collectors.toList());
    }

    @Override
    public List<NoteDto> getAllArchived(User user) {
        List<Note> notes = noteService.getAllArchivedNotes(user);
        return notes.stream().map(noteDtoConverter::convertToDtoIgnoreNull).collect(Collectors.toList());
    }

    @Override
    public void update(NoteDto noteDto, User user) {
        Note note = noteDtoConverter.convertToEntityIgnoreNull(noteDto);
        note.setUser(user);
        noteService.update(note);
    }

    @Override
    public Optional<NoteDto> getById(Long id) {
        return noteService.get(id).map(noteDtoConverter::convertToDtoIgnoreNull);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        noteService.delete(id);
    }

    @Override
    public void archive(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        Optional<Note> note = noteService.get(id);
        note.ifPresent(n -> {
            n.setArchived(true);
            n.setArchivedAt(LocalDate.now());
            noteService.update(n);
        });
    }

    @Override
    public void unarchive(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        Optional<Note> note = noteService.get(id);
        note.ifPresent(n -> {
            n.setArchived(false);
            n.setArchivedAt(null);
            noteService.update(n);
        });
    }

    @Override
    public List<NoteDto> getAllDoneUnarchived(User user) {
        List<Note> notes = noteService.getAllDoneUnarchivedNotes(user);
        return notes.stream().map(noteDtoConverter::convertToDtoIgnoreNull).collect(Collectors.toList());
    }

    @Override
    public List<NoteDto> getAllUnarchivedByCreated(User user, LocalDate created) {
        List<Note> notes = noteService.getAllUnarchivedByCreated(user, created);
        return notes.stream().map(noteDtoConverter::convertToDtoIgnoreNull).collect(Collectors.toList());
    }

    @Override
    public List<NoteDto> getAllNotDoneUnarchived(User user) {
        List<Note> notes = noteService.getAllNotDoneUnarchivedNotes(user);
        return notes.stream().map(noteDtoConverter::convertToDtoIgnoreNull).collect(Collectors.toList());
    }
}
