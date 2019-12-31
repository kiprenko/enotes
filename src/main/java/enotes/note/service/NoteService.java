package enotes.note.service;

import java.util.List;

import enotes.note.Note;

public interface NoteService {

    Note save(Note note);

    long delete(Note note);

    long delete(long id);

    Note get(Note note);

    Note get(long id);

    Note update(Note note);

    List<Note> getAllNotes();
}
