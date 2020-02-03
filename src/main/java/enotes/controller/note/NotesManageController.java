package enotes.controller.note;

import enotes.note.Note;
import enotes.note.service.NoteService;
import enotes.user.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequestMapping("/note")
@Controller
public class NotesManageController {

    private NoteService noteService;

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping(value = "/new")
    public String createNewNote(Model model) {
        LOGGER.info("Create page joined");
        model.addAttribute("note", new Note());
        return "noteManage/createNewNote.html";
    }

    @PostMapping(value = "/saveNote")
    public String saveNote(Note note) {
        //the temporary solution while i don't have a authorization mechanism
        User user = new User();
        user.setId(1L);
        note.setUser(user);

        LOGGER.info("Saving a new note");
        noteService.save(note);
        return "redirect:/notesGalleryView";
    }

    @PostMapping(value = "/update")
    public String updateNote(Note note) {
        LOGGER.info("Updating a note with id = {}", note.getId());
        User user = new User();
        user.setId(1L);
        note.setUser(user);
        noteService.update(note);
        return "redirect:/note/" + note.getId();
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        LOGGER.info("Deleting note with id = {}", id);
        if (noteService.delete(id) == -1) {
            LOGGER.error("Note with id = {} wasn't deleted", id);
        }
        return "redirect:/notesGalleryView";
    }


    @GetMapping("/{id}")
    public String viewNote(@PathVariable Long id, Model model) {
        model.addAttribute("note", noteService.get(id));
        return "noteManage/viewNote.html";
    }
}
