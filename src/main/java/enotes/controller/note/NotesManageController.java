package enotes.controller.note;

import enotes.entity.note.Note;
import enotes.entity.note.service.NoteService;
import enotes.entity.user.User;
import enotes.entity.user.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Log4j2
@RequestMapping("/note")
@Controller
public class NotesManageController {

    private NoteService noteService;
    private UserService userService;

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/new")
    public String createNewNote(Model model) {
        model.addAttribute("note", new Note());
        return "noteManage/createNewNote.html";
    }

    @PostMapping(value = "/saveNote")
    public String saveNote(Note note) {
        LOGGER.info("Saving a new note");
        Optional<User> user = userService.get(1L);
        if (user.isPresent()) {
            note.setUser(user.get());
            noteService.save(note);
            return "redirect:/notesGalleryView";
        }

        return "noteManage/createNewNote.html";
    }

    @PostMapping(value = "/update")
    public String updateNote(Note note) {
        LOGGER.info("Updating a note with id = {}", note.getId());
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
        noteService.get(id).ifPresent(note -> model.addAttribute("note", note));
        return "noteManage/viewNote.html";
    }
}
