package enotes.controller.note;

import enotes.dto.note.NoteDto;
import enotes.dto.note.NoteDtoConverter;
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

    private final NoteService noteService;
    private final UserService userService;
    private final NoteDtoConverter noteDtoConverter;

    @Autowired
    public NotesManageController(NoteService noteService,
                                 UserService userService,
                                 NoteDtoConverter noteDtoConverter) {
        this.noteService = noteService;
        this.userService = userService;
        this.noteDtoConverter = noteDtoConverter;
    }

    @GetMapping(value = "/new")
    public String createNewNote(Model model) {
        model.addAttribute("note", new NoteDto());
        return "noteManage/createNewNote.html";
    }

    @PostMapping(value = "/saveNote")
    public String saveNote(NoteDto note) {
        LOGGER.info("Saving a new note");
        Optional<User> user = userService.get(1L);
        if (user.isPresent()) {
            Note noteEntity = noteDtoConverter.convertToEntity(note);
            noteEntity.setUser(user.get());
            noteService.save(noteEntity);
            return "redirect:/notesGalleryView";
        }

        return "noteManage/createNewNote.html";
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
        return "noteManage/viewNote.html";
    }
}
