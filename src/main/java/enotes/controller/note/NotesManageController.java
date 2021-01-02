package enotes.controller.note;

import enotes.data.note.NoteManager;
import enotes.data.user.User;
import enotes.data.user.UserService;
import enotes.dto.note.NoteDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Log4j2
@RequestMapping("/note")
@Controller
public class NotesManageController {

    private final UserService userService;
    private final NoteManager noteManager;

    @Autowired
    public NotesManageController(UserService userService, NoteManager noteManager) {
        this.userService = userService;
        this.noteManager = noteManager;
    }

    @GetMapping(value = "/new")
    public String createNewNote(Model model) {
        model.addAttribute("note", new NoteDto());
        return "noteManage/createNewNote";
    }

    @PostMapping(value = "/saveNote")
    public String saveNote(NoteDto note, Principal principal) {
        Optional<User> user = userService.getByEmail(principal.getName());
        if (user.isPresent()) {
            noteManager.saveNew(note, user.get());
            return "redirect:/notesGalleryView";
        }

        return "noteManage/createNewNote";
    }

    @PostMapping(value = "/update")
    public String updateNote(NoteDto note, Principal principal) {
        LOGGER.info("Updating a note with id = {}", note.getId());
        Optional<User> user = userService.getByEmail(principal.getName());
        user.ifPresent(value -> noteManager.update(note, value));
        return "redirect:/note/" + note.getId();
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        LOGGER.info("Deleting note with id = {}", id);
        noteManager.deleteById(id);
        return "redirect:/notesGalleryView";
    }


    @GetMapping("/{id}")
    public String viewNote(@PathVariable Long id, Model model) {
        noteManager.getById(id).ifPresent(n -> model.addAttribute("note", n));
        return "noteManage/viewNote";
    }

    @GetMapping(value = "/archive/{id}")
    public String archiveNote(@PathVariable Long id) {
        noteManager.archive(id);
        return "redirect:/note/" + id;
    }

    @GetMapping(value = "/unarchive/{id}")
    public String unarchiveNote(@PathVariable Long id) {
        noteManager.unarchive(id);
        return "redirect:/note/" + id;
    }
}
