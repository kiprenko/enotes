package enotes.note.service;

import enotes.note.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    void save(Note note);

    long delete(Note note);

    long delete(long id);

    Optional<Note> get(Note note);

    Optional<Note> get(long id);

    void update(Note note);

    List<Note> getAllNotes();
}
