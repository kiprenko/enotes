package com.enotes.note.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.enotes.note.Note;
import com.enotes.note.NoteState;

@Service
public class NoteServiceSimpleImpl implements NoteService {

    private static List<Note> notes = new ArrayList<>();

    static {
        Note note = new Note();
        note.setState(NoteState.HIGH);
        note.setText("Very important text, VERY!");
        note.setHeader("Very important header.");

        Note note1 = new Note();
        note1.setState(NoteState.MIDDLE);
        note1.setText("Not so important by be careful");
        note1.setHeader("Middle priority header");

        Note note2 = new Note();
        note2.setState(NoteState.LOW);
        note2.setText("Nah, take it easy, we have plenty of time");
        note2.setHeader("Lowly header!");

        Note note3 = new Note();
        note3.setState(NoteState.MIDDLE);
        note3.setText("Not so important by be careful");
        note3.setHeader("Middle priority header");

        Note note4 = new Note();
        note4.setState(NoteState.LOW);
        note4.setText("Nah, take it easy, we have plenty of time");
        note4.setHeader("Lowly header!");

        Note note5 = new Note();
        note5.setState(NoteState.LOW);
        note5.setText("Nah, take it easy, we have plenty of time");
        note5.setHeader("Lowly header!");

        notes.add(note);
        notes.add(note1);
        notes.add(note2);
        notes.add(note3);
        notes.add(note4);
        notes.add(note5);
        notes.add(note);
        notes.add(note1);
        notes.add(note2);
        notes.add(note3);
        notes.add(note4);
        notes.add(note5);

        int id = 1;
        for (Note not : notes) {
            not.setId(id++);
        }
    }

    @Override
    public  Note create(Note note)
    {
        notes.add(note);
        return note;
    }

    @Override
    public long delete(Note note)
    {
        notes.remove(note);
        return note.getId();
    }

    @Override
    public long delete(long id)
    {
        Optional<Note> optional = notes.stream()
                                        .filter(x -> id == x.getId())
                                        .findFirst();
        if (optional.isPresent()) {
            notes.remove(optional.get());
            return optional.get().getId();
        }
        return 0;
    }

    @Override
    public Note get(Note note)
    {
        Optional<Note> optional = notes.stream()
                                       .filter(x -> note.equals(x.getId()))
                                       .findFirst();
        return optional.orElse(null);
    }

    @Override
    public Note get(long id)
    {
        Optional<Note> optional = notes.stream()
                                       .filter(x -> id == x.getId())
                                       .findFirst();
        return optional.orElse(null);
    }

    @Override
    public Note update(Note note)
    {
        Optional<Note> optional = notes.stream()
                                       .filter(x -> note.equals(x.getId()))
                                       .findFirst();
        if (optional.isPresent()) {
            optional.get().setState(note.getState());
            optional.get().setHeader(note.getHeader());
            optional.get().setText(note.getText());
            optional.get().setComments(note.getComments());
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Note> getAllNotes()
    {
        return notes;
    }
}
