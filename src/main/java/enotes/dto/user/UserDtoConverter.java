package enotes.dto.user;

import enotes.dto.DtoConverter;
import enotes.data.user.User;

public interface UserDtoConverter extends DtoConverter<UserDto, User> {

    UserDto convertToDtoIgnoreNull(User entity);

    User convertToEntityIgnoreNull(UserDto dto);
}
