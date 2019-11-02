package com.enotes.note.service;

import com.enotes.note.Note;
import com.enotes.note.dao.NoteDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class NoteServiceImpl implements NoteService {

    private NoteDao noteDao;

    @Autowired
    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public Note create(Note note) {
        if (note == null) {
            LOGGER.error("Note can't be null!");
            return null;
        }
        if (noteDao.add(note)) {
            return note;
        }
        return null;
    }

    @Override
    public long delete(Note note) {
        if (note == null) {
            LOGGER.error("Note can't be null!");
            return -1;
        } else if (note.getId() == 0) {
            LOGGER.error("Can't delete note with id less than 1. Note with header {} and body {}", note.getHeader(), note.getBody());
            return -1;
        }
        noteDao.delete(note.getId());
        return note.getId();
    }

    @Override
    public long delete(long id) {
        if (id < 1) {
            LOGGER.error("Can't delete note with id less than 1");
            return -1;
        }

        noteDao.delete(id);
        return id;
    }

    @Override
    public Note get(Note note) {
        if (note == null) {
            LOGGER.error("Note can't be null!");
            return null;
        } else if (note.getId() < 1) {
            LOGGER.error("Can't find note with id less than 1. Id is {}", note.getId());
            return null;
        }
        return noteDao.find(note.getId());
    }

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
        } else if (note.getId() < 1) {
            LOGGER.error("Can't update note with id less than 1. Id is {}", note.getId());
            return null;
        }
        noteDao.update(note);
        return note;
    }

    @Override
    public List<Note> getAllNotes() {
        return noteDao.findAll();
    }
}
