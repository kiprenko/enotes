package enotes.dto.comment;

import enotes.dto.note.NoteDtoConverter;
import enotes.dto.user.UserDtoConverter;
import enotes.data.comment.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoConverterImpl implements CommentDtoConverter {

    private static final String USER_FIELD = "user";
    private static final String NOTE_FIELD = "note";

    private final NoteDtoConverter noteDtoConverter;
    private final UserDtoConverter userDtoConverter;

    @Autowired
    public CommentDtoConverterImpl(NoteDtoConverter noteDtoConverter, UserDtoConverter userDtoConverter) {
        this.noteDtoConverter = noteDtoConverter;
        this.userDtoConverter = userDtoConverter;
    }

    @Override
    public CommentDto convertToDto(Comment entity) {
        CommentDto dto = new CommentDto();
        BeanUtils.copyProperties(entity, dto, USER_FIELD, NOTE_FIELD);
        dto.setUser(userDtoConverter.convertToDto(entity.getUser()));
        dto.setNote(noteDtoConverter.convertToDto(entity.getNote()));
        return dto;
    }

    @Override
    public Comment convertToEntity(CommentDto dto) {
        Comment entity = new Comment();
        BeanUtils.copyProperties(dto, entity, USER_FIELD, NOTE_FIELD);
        entity.setUser(userDtoConverter.convertToEntity(dto.getUser()));
        entity.setNote(noteDtoConverter.convertToEntity(dto.getNote()));
        return entity;
    }
}
