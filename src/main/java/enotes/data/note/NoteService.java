package enotes.data.note;

import enotes.data.user.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NoteService {

    void save(Note note);

    long delete(Note note);

    long delete(long id);

    Optional<Note> get(long id);

    void update(Note note);

    List<Note> getAllUnarchivedNotes(User user);

    List<Note> getAllDoneUnarchivedNotes(User user);

    List<Note> getAllNotDoneUnarchivedNotes(User user);

    List<Note> getAllArchivedNotes(User user);

    List<Note> getAllUnarchivedByCreated(User user, LocalDate created);
}
