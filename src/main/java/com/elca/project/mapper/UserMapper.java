package com.elca.project.mapper;


import com.elca.project.dto.UserDto;
import com.elca.project.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoToEntity (UserDto userDto);
    UserDto userEntityToDto(User user);

}
