package com.enotes.note.dao;

import com.enotes.note.Note;

import java.util.stream.Stream;

public interface NoteDao {
    Stream<Note> getAll();
    Note getById(long id);
    boolean add(Note note);
    boolean update(Note note);
    boolean delete(Note note);
}
