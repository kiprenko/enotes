package enotes.controller.note;

import enotes.data.note.NoteManager;
import enotes.data.user.User;
import enotes.data.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;

@Log4j2
@Controller
public class NotesViewController {

    private final NoteManager noteManager;
    private final UserService userService;

    @Autowired
    public NotesViewController(NoteManager noteManager, UserService userService) {
        this.noteManager = noteManager;
        this.userService = userService;
    }

    @GetMapping("/notesGalleryView")
    public String viewNotesGallery(Model model, Principal principal) {
        Optional<User> user = userService.getByEmail(principal.getName());
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("Username '%s' not found in DB.", principal.getName()));
        }
        model.addAttribute("notes", noteManager.getAllUnarchived(user.get()));
        return "notesGalleryView";
    }

    @GetMapping("/viewArchivedNotes")
    public String viewArchivedNotes(Model model, Principal principal) {
        Optional<User> user = userService.getByEmail(principal.getName());
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("Username '%s' not found in DB.", principal.getName()));
        }
        model.addAttribute("notes", noteManager.getAllArchived(user.get()));
        return "viewArchivedNotes";
    }
}
