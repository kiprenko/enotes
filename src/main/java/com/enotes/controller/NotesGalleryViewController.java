package com.enotes.controller;

import com.enotes.note.service.NoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
public class NotesGalleryViewController {
    private NoteService noteService;

//    @Autowired
    public void setNoteService(NoteService noteService)
    {
        this.noteService = noteService;
    }

    @RequestMapping("/notesGalleryView")
    public String index(Model model) {
        LOGGER.info("Sending gallery view: count of notes {}", noteService.getAllNotes().size());
        model.addAttribute("notes", noteService.getAllNotes());
        return "notesGalleryView.html";
    }
}
