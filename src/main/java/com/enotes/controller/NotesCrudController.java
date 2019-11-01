package com.enotes.controller;

import com.enotes.note.Note;
import com.enotes.note.dao.NoteDao;
import com.enotes.user.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j2
@RequestMapping("/note")
@Controller
public class NotesCrudController {

    private NoteDao noteDao;

    @Autowired
    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createNewNote(Model model) {
        LOGGER.info("Create page joined");
        model.addAttribute("note", new Note());
        return "noteCrud/createNewNote.html";
    }

    @RequestMapping(value = "/saveNote", method = RequestMethod.POST)
    public String saveNote(Note note) {
        //the temporary solution while i don't have a authorization mechanism
        User user = new User();
        user.setId(1L);
        note.setUser(user);

        LOGGER.info("Saving a new note");
        if (noteDao.add(note)) {
            LOGGER.info("Note was successfully saved.");
        } else {
            LOGGER.error("Note wasn't saved.");
        }
        return "redirect:/notesGalleryView";
    }

    @RequestMapping("/{id}")
    public String viewNote(@PathVariable Long id, Model model) {
        model.addAttribute("note", noteDao.find(id));
        return "noteCrud/viewNote.html";
    }
}
