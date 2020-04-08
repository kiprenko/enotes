package enotes.dto.user;

import enotes.dto.DtoConverter;
import enotes.entity.user.User;

public interface UserDtoConverter extends DtoConverter<UserDto, User> {

    UserDto convertToDtoSkipNull(User entity);

    User convertToEntitySkipNull(UserDto dto);
}
