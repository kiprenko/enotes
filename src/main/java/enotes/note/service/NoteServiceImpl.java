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

    private NoteDao noteDao;

    @Autowired
    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public void save(Note note) {
        if (note == null) {
            LOGGER.error("Note can't be null!");
            return;
        }

        noteDao.add(note);
    }

    @Cache
    @Override
    public long delete(Note note) {
        if (note == null) {
            LOGGER.error("Note can't be null!");
            return -1;
        }

        Long noteId = note.getId();
        if (noteId < 1) {
            LOGGER.error("Can't delete note with id less than 1. Note with header {} and body {}", note.getHeader(), note.getBody());
            return -1;
        }

        noteDao.delete(noteId);
        return noteId;
    }

    @Override
    public long delete(long id) {
        if (id < 1) {
            LOGGER.error("Can't delete note with id less than 1. Id = {}", id);
            return -1;
        }

        noteDao.delete(id);
        return id;
    }

    @Cache
    @Override
    public Optional<Note> get(Note note) {
        if (note == null) {
            LOGGER.error("Note can't be null!");
            return null;
        }

        Long noteId = note.getId();
        if (noteId < 1) {
            LOGGER.error("Can't find note with id less than 1. Id is {}", noteId);
            return null;
        }

        return noteDao.find(noteId);
    }

    @Cache
    @Override
    public Optional<Note> get(long id) {
        if (id < 1) {
            LOGGER.error("Can't find note with id less than 1. Id is {}", id);
            throw new IllegalArgumentException();
        }

        return noteDao.find(id);
    }

    @Override
    public void update(Note note) {
        if (note == null) {
            LOGGER.error("Note can't be null!");
            throw new IllegalArgumentException();
        }

        Long noteId = note.getId();
        if (noteId < 1) {
            LOGGER.error("Can't update note with id less than 1. Id is {}", noteId);
            throw new IllegalArgumentException();
        }

        noteDao.update(note);
    }

    @Cache
    @Override
    public List<Note> getAllNotes() {
        return noteDao.findAll();
    }
}
