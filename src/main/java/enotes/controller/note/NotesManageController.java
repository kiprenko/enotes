package enotes.controller.note;

import enotes.dto.note.NoteDto;
import enotes.dto.note.NoteDtoConverter;
import enotes.dto.user.UserDtoConverter;
import enotes.data.note.Note;
import enotes.data.note.NoteService;
import enotes.data.user.User;
import enotes.data.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

@Log4j2
@RequestMapping("/note")
@Controller
public class NotesManageController {

    private final NoteService noteService;
    private final UserService userService;
    private final NoteDtoConverter noteDtoConverter;
    private final UserDtoConverter userDtoConverter;

    @Autowired
    public NotesManageController(NoteService noteService,
                                 UserService userService,
                                 NoteDtoConverter noteDtoConverter,
                                 UserDtoConverter userDtoConverter) {
        this.noteService = noteService;
        this.userService = userService;
        this.noteDtoConverter = noteDtoConverter;
        this.userDtoConverter = userDtoConverter;
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
            Note noteEntity = noteDtoConverter.convertToEntitySkipNull(note);
            noteEntity.setUser(user.get());
            noteEntity.setCreated(LocalDate.now());
            noteService.save(noteEntity);
            return "redirect:/notesGalleryView";
        }

        return "noteManage/createNewNote";
    }

    @PostMapping(value = "/update")
    public String updateNote(NoteDto note) {
        LOGGER.info("Updating a note with id = {}", note.getId());
        noteService.update(noteDtoConverter.convertToEntity(note));
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
        noteService.get(id).ifPresent(n -> model.addAttribute("note", noteDtoConverter.convertToDto(n)));
        return "noteManage/viewNote";
    }
}
