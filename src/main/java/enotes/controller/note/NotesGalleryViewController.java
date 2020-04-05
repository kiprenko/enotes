package enotes.controller.note;

import enotes.dto.note.NoteDto;
import enotes.dto.note.NoteDtoConverter;
import enotes.entity.note.service.NoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Controller
public class NotesGalleryViewController {

    private final NoteService noteService;
    private final NoteDtoConverter noteDtoConverter;

    @Autowired
    public NotesGalleryViewController(NoteService noteService, NoteDtoConverter noteDtoConverter) {
        this.noteService = noteService;
        this.noteDtoConverter = noteDtoConverter;
    }

    @GetMapping("/notesGalleryView")
    public String index(Model model) {
        List<NoteDto> notes = noteService.getAllNotes().stream()
                                                       .map(noteDtoConverter::convertToDto)
                                                       .collect(Collectors.toList());
        model.addAttribute("notes", notes);
        LOGGER.info("Showing all list of notes. List size is " + notes.size());
        return "notesGalleryView.html";
    }
}
