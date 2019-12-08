package enotes.controller;

import enotes.note.Note;
import enotes.note.dao.NoteDao;
import enotes.note.service.NoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@Controller
public class NotesGalleryViewController {

    private NoteService noteService;

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping("/notesGalleryView")
    public String index(Model model) {
        List<Note> notes = noteService.getAllNotes();
        model.addAttribute("notes", notes);
        LOGGER.info("Showing all list of notes. List size is " + notes.size());
        return "notesGalleryView.html";
    }
}
