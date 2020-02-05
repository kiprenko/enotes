package enotes.note.service;

import enotes.annotation.cache.Cache;
import enotes.note.Note;
import enotes.note.dao.NoteDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteDao noteDao;

    @Autowired
    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public void save(Note note) {
        if (note == null) {
            throw new IllegalArgumentException();
        }

        noteDao.add(note);
    }

    @Cache
    @Override
    public long delete(Note note) {
        if (note == null) {
            throw new IllegalArgumentException();
        }

        Long noteId = note.getId();
        if (noteId < 1) {
            throw new IllegalArgumentException(String.format("Can't delete note with id less than 1. Note with header %s and body %s",
                    note.getHeader(),
                    note.getBody()));
        }

        noteDao.delete(noteId);
        return noteId;
    }

    @Override
    public long delete(long id) {
        if (id < 1) {
            throw new IllegalArgumentException(String.format("Can't delete note with id less than 1. Id = %d", id));
        }

        noteDao.delete(id);
        return id;
    }

    @Cache
    @Override
    public Optional<Note> get(Note note) {
        if (note == null) {
            throw new IllegalArgumentException();
        }

        Long noteId = note.getId();
        if (noteId < 1) {
            throw new IllegalArgumentException(String.format("Can't find note with id less than 1. Id is %d", noteId));
        }

        return noteDao.find(noteId);
    }

    @Cache
    @Override
    public Optional<Note> get(long id) {
        if (id < 1) {
            throw new IllegalArgumentException(String.format("Can't find note with id less than 1. Id = %d", id));
        }

        return noteDao.find(id);
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

        noteDao.update(note);
    }

    @Cache
    @Override
    public List<Note> getAllNotes() {
        return noteDao.findAll();
    }
}
