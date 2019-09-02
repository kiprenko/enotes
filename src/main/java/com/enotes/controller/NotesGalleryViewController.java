package com.enotes.controller;

import com.enotes.note.Note;
import com.enotes.note.NoteState;
import com.enotes.note.dao.JdbcNoteDao;
import com.enotes.note.dao.NoteDao;
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

    private NoteDao noteDao;

    @RequestMapping("/notesGalleryView")
    public String index(Model model) {
        List<Note> notes = noteDao.getAll();
        model.addAttribute("notes", notes);
        LOGGER.info("Showing all list of notes. List size is " + notes.size());
        return "notesGalleryView.html";
    }

    @Autowired
    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }
}
