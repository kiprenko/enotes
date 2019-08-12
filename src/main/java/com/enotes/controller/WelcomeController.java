package com.enotes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enotes.note.Note;
import com.enotes.note.NoteState;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class WelcomeController {

    @RequestMapping("/")
    public String index(Model model) {
        log.info("Gotcha!");
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

        List<Note> notes = new ArrayList<>();
        notes.add(note);
        notes.add(note1);
        notes.add(note2);
        notes.add(note3);
        notes.add(note4);
        notes.add(note5);
        model.addAttribute("notes", notes);
        log.info("Response was sent!");
        return "index.html";
    }
}
