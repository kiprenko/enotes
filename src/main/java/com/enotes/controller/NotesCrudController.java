package com.enotes.controller;

import com.enotes.note.dao.NoteDao;
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
        model.addAttribute(model);
        return "noteCrud/createNewNote.html";
    }

    @RequestMapping("/{id}")
    public String viewNote(@PathVariable Long id, Model model) {
        model.addAttribute("note", noteDao.find(id));
        return "noteCrud/viewNote.html";
    }
}
