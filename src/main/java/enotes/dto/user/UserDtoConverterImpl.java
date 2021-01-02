package enotes.dto.user;

import enotes.data.user.User;
import enotes.data.userrole.UserRole;
import enotes.data.userrole.UserRoleEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverterImpl implements UserDtoConverter {

    private static final String ROLE_FIELD = "role";

    @Override
    public UserDto convertToDto(User entity) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(entity, dto, ROLE_FIELD);
        dto.setRole(UserRoleEnum.getRoleByName(entity.getRole().getRole()));
        return dto;
    }

    @Override
    public User convertToEntity(UserDto dto) {
        User entity = new User();
        BeanUtils.copyProperties(dto, entity, ROLE_FIELD);
        entity.setRole(new UserRole(dto.getRole().getRoleId(), dto.getRole().getRoleName()));
        return entity;
    }

    @Override
    public UserDto convertToDtoIgnoreNull(User entity) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(entity, dto, getNullPropertyNames(entity));
        dto.setRole(UserRoleEnum.getRoleByName(entity.getRole().getRole()));
        return dto;
    }

    @Override
    public User convertToEntityIgnoreNull(UserDto dto) {
        User entity = new User();
        BeanUtils.copyProperties(dto, entity, getNullPropertyNames(dto));
        entity.setRole(new UserRole(dto.getRole().getRoleId(), dto.getRole().getRoleName()));
        return entity;
    }
}
