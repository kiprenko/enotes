package enotes.dto.note;

import enotes.data.note.Note;
import enotes.dto.DtoConverter;

public interface NoteDtoConverter extends DtoConverter<NoteDto, Note> {
    NoteDto convertToDtoIgnoreNull(Note entity);

    Note convertToEntityIgnoreNull(NoteDto dto);
}
