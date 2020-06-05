package com.elca.project.service.impl;

import com.elca.project.dto.UserDto;
import com.elca.project.entity.User;
import com.elca.project.mapper.UserMapper;
import com.elca.project.repository.UserRepository;
import com.elca.project.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;
    final private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository=userRepository;
        this.userMapper=userMapper;
    }

    //get by Id
    @Override
    public UserDto getUserById(long userId) {
        Optional<User> getUserById = userRepository.findById(userId);
        User user = getUserById.orElseThrow(null);
        return userMapper.userEntityToDto(user);
    }

    //getting all user
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::userEntityToDto).collect(Collectors.toList());
    }

    //inserting user
    @Override
    public void saveUser(UserDto userDto) {
        User newUser = userMapper.userDtoToEntity(userDto);
        userRepository.save(newUser);
    }


    //update
    @Override
    public void updateUser(UserDto userDto) {
        UserDto userEntity = getUserById(userDto.getUserId());

        userEntity.setUserId(userDto.getUserId());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        userRepository.save(userMapper.userDtoToEntity(userEntity));
    }

    @Override
    public void deleteUser(UserDto userDto, long userId) {

    }

}
