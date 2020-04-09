package enotes.dto.note;

import enotes.dto.DtoConverter;
import enotes.data.note.Note;

public interface NoteDtoConverter extends DtoConverter<NoteDto, Note> {
    NoteDto convertToDtoSkipNull(Note entity);

    Note convertToEntitySkipNull(NoteDto dto);
}
