package enotes.dto.note;

import enotes.entity.note.Note;
import org.springframework.beans.BeanUtils;

public class NoteDtoConverterImpl implements NoteDtoConverter {
    @Override
    public NoteDto convertToDto(Note entity) {
        NoteDto dto = new NoteDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public Note convertToEntity(NoteDto dto) {
        Note entity = new Note();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
