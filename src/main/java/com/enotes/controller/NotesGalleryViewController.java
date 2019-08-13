package com.enotes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enotes.note.Note;
import com.enotes.note.NoteState;
import com.enotes.note.service.NoteService;
import com.enotes.note.service.NoteServiceSimpleImpl;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class NotesGalleryViewController {
    private NoteService noteService;

    @Autowired
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
