package com.enotes.controller;

import com.enotes.note.Note;
import com.enotes.note.NoteState;
import com.enotes.note.dao.JdbcNoteDao;
import com.enotes.note.service.NoteService;
import com.enotes.user.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
public class NotesGalleryViewController {

    @RequestMapping("/notesGalleryView")
    public String index(Model model) {
        User user = new User();
        user.setId(1L);
        Note note = new Note();
        note.setHeader("Header for this note");
        note.setBody("Body of this note");
        note.setState(NoteState.MIDDLE);

        JdbcNoteDao dao = new JdbcNoteDao();
        dao.add(note);

        List<Note> notes = new ArrayList<>();
        notes.add(note);
        model.addAttribute("notes", notes);
        return "notesGalleryView.html";
    }
}
