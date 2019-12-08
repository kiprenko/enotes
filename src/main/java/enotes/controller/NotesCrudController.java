package com.enotes.controller;

import com.enotes.note.Note;
import com.enotes.note.service.NoteService;
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

    private NoteService noteService;

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
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
        if (noteService.create(note) != null) {
            LOGGER.info("Note was successfully saved.");
        } else {
            LOGGER.error("Note wasn't saved.");
        }
        return "redirect:/notesGalleryView";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateNote(Note note) {
        LOGGER.info("Updating a note with id = {}", note.getId());
        noteService.update(note);
        return "redirect:/note/" + note.getId();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteNote(@PathVariable Long id) {
        LOGGER.info("Deleting note with id = {}", id);
        if (noteService.delete(id) == -1) {
            LOGGER.error("Note with id = {} wasn't deleted", id);
        }
        return "redirect:/notesGalleryView";
    }


    @RequestMapping("/{id}")
    public String viewNote(@PathVariable Long id, Model model) {
        model.addAttribute("note", noteService.get(id));
        return "noteCrud/viewNote.html";
    }
}
