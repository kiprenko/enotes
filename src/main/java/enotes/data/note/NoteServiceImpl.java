package enotes.data.note;

import enotes.annotation.cache.Cache;
import enotes.data.user.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public void save(Note note) {
        if (note == null) {
            throw new IllegalArgumentException();
        }

        noteRepository.save(note);
    }

    @Cache
    @Override
    public long delete(Note note) {
        if (note == null) {
            throw new IllegalArgumentException();
        }

        noteRepository.delete(note);
        return note.getId();
    }

    @Override
    public long delete(long id) {
        if (id < 1) {
            throw new IllegalArgumentException(String.format("Can't delete note with id less than 1. Id = %d", id));
        }

        noteRepository.deleteById(id);
        return id;
    }

    @Cache
    @Override
    public Optional<Note> get(long id) {
        if (id < 1) {
            throw new IllegalArgumentException(String.format("Can't find note with id less than 1. Id = %d", id));
        }

        return noteRepository.findById(id);
    }

    @Override
    public void update(Note note) {
        if (note == null) {
            throw new IllegalArgumentException();
        }

        Long noteId = note.getId();
        if (noteId < 1) {
            throw new IllegalArgumentException(String.format("Can't update note with id less than 1. Id = %d", noteId));
        }
        note.setLastModified(LocalDate.now());
        noteRepository.save(note);
    }

    @Override
    public List<Note> getAllUnarchivedNotes(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        return noteRepository.findAllByUserAndIsArchivedOrderByCreatedDesc(user, false);
    }

    @Override
    public List<Note> getAllDoneUnarchivedNotes(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        return noteRepository.findAllByUserAndIsArchivedAndIsDoneOrderByCreatedDesc(user, false, true);
    }

    @Override
    public List<Note> getAllNotDoneUnarchivedNotes(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        return noteRepository.findAllByUserAndIsArchivedAndIsDoneOrderByCreatedDesc(user, false, false);
    }

    @Override
    public List<Note> getAllArchivedNotes(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        return noteRepository.findAllByUserAndIsArchivedOrderByCreatedDesc(user, true);
    }

    @Override
    public List<Note> getAllUnarchivedByCreated(User user, LocalDate created) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        return noteRepository.findAllByUserAndIsArchivedAndCreated(user, false, created);
    }
}
