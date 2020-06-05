package com.elca.project.service;

import com.elca.project.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(long userId);

    List<UserDto> getAllUsers();

    void saveUser(UserDto userDto);

    void updateUser(UserDto userDto);

    void deleteUser(UserDto userDto, long userId);


}
