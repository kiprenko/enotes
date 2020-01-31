package enotes.note.service;

import enotes.annotation.cache.Cache;
import enotes.note.Note;
import enotes.note.dao.NoteDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class NoteServiceImpl implements NoteService {

    private NoteDao noteDao;

    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public Note save(Note note) {
        if (note == null) {
            LOGGER.error("Note can't be null!");
            return null;
        }

        noteDao.add(note);
        return note;
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
    public Note get(Note note) {
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
    public Note get(long id) {
        if (id < 1) {
            LOGGER.error("Can't find note with id less than 1. Id is {}", id);
            return null;
        }

        return noteDao.find(id);
    }

    @Override
    public Note update(Note note) {
        if (note == null) {
            LOGGER.error("Note can't be null!");
            return null;
        }

        Long noteId = note.getId();
        if (noteId < 1) {
            LOGGER.error("Can't update note with id less than 1. Id is {}", noteId);
            return null;
        }

        noteDao.update(note);
        return note;
    }

    @Cache
    @Override
    public List<Note> getAllNotes() {
        return noteDao.findAll();
    }
}
