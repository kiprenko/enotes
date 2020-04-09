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
public class NotesGalleryViewController {

    private final NoteManager noteManager;
    private final UserService userService;

    @Autowired
    public NotesGalleryViewController(NoteManager noteManager, UserService userService) {
        this.noteManager = noteManager;
        this.userService = userService;
    }

    @GetMapping("/notesGalleryView")
    public String index(Model model, Principal principal) {
        Optional<User> user = userService.getByEmail(principal.getName());
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("Username '%s' not found in DB.", principal.getName()));
        }
        model.addAttribute("notes", noteManager.getAll(user.get()));
        return "notesGalleryView";
    }
}
