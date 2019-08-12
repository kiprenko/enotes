package com.enotes.controller;

import com.enotes.note.Note;
import com.enotes.note.NoteState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @RequestMapping("/")
    public String index(Model model) {
        Note note = new Note();
        note.setState(NoteState.MIDDLE);
        note.setText("Holy shit!");
        note.setHeader("Too cool to be header!");
        model.addAttribute(note);
        return "index.html";
    }
}
