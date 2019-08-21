package com.enotes.note.dao;

import com.enotes.note.Note;

import java.util.stream.Stream;

public class NoteDaoImpl implements NoteDao {

    @Override
    public Stream<Note> getAll() {
        return null;
    }

    @Override
    public Note getById(Long id) {
        return null;
    }

    @Override
    public boolean add(Note note) {
        return false;
    }

    @Override
    public boolean update(Note note) {
        return false;
    }

    @Override
    public boolean delete(Note note) {
        return false;
    }
}
