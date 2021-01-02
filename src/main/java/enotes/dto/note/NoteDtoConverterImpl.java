package enotes.dto.note;

import enotes.data.note.Note;
import enotes.dto.user.UserDtoConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoteDtoConverterImpl implements NoteDtoConverter {

    private static final String USER_FIELD = "user";

    private final UserDtoConverter userDtoConverter;

    @Autowired
    public NoteDtoConverterImpl(UserDtoConverter userDtoConverter) {
        this.userDtoConverter = userDtoConverter;
    }

    @Override
    public NoteDto convertToDto(Note entity) {
        NoteDto dto = new NoteDto();
        BeanUtils.copyProperties(entity, dto, USER_FIELD);
        dto.setUser(userDtoConverter.convertToDto(entity.getUser()));
        return dto;
    }

    @Override
    public Note convertToEntity(NoteDto dto) {
        Note entity = new Note();
        BeanUtils.copyProperties(dto, entity, USER_FIELD);
        entity.setUser(userDtoConverter.convertToEntity(dto.getUser()));
        return entity;
    }

    @Override
    public NoteDto convertToDtoIgnoreNull(Note entity) {
        NoteDto dto = new NoteDto();
        BeanUtils.copyProperties(entity, dto, getNullPropertyNames(entity));
        return dto;
    }

    @Override
    public Note convertToEntityIgnoreNull(NoteDto dto) {
        Note entity = new Note();
        BeanUtils.copyProperties(dto, entity, getNullPropertyNames(dto));
        return entity;
    }
}
