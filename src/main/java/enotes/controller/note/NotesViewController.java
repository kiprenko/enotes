package enotes.controller.note;

import enotes.data.note.NoteManager;
import enotes.data.user.User;
import enotes.data.user.UserService;
import enotes.dto.note.NoteDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
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
    public String viewUnarchivedNotes(Model model, Principal principal,
                                      @RequestParam(required = false, name = "filterDone") Boolean filterDone,
                                      @RequestParam(required = false, name = "filterToday") Boolean filterToday,
                                      @RequestParam(required = false, name = "filterYesterday") Boolean filterYesterday) {
        Optional<User> user = userService.getByEmail(principal.getName());
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("Username '%s' not found in DB.", principal.getName()));
        }

        List<NoteDto> notes;
        if (filterDone != null && filterDone) {
            notes = noteManager.getAllDoneUnarchived(user.get());

        } else if (filterDone != null) {
            notes = noteManager.getAllNotDoneUnarchived(user.get());
        } else if (filterToday != null && filterToday) {
            notes = noteManager.getAllUnarchivedByCreated(user.get(), LocalDate.now());
        } else if (filterYesterday != null && filterYesterday) {
            notes = noteManager.getAllUnarchivedByCreated(user.get(), LocalDate.now().minusDays(1));
        } else {
            notes = noteManager.getAllUnarchived(user.get());
        }

        model.addAttribute("notes", notes);
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
