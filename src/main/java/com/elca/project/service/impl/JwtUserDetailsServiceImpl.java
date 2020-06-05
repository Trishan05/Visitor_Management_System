package com.elca.project.service.impl;

import com.elca.project.dto.UserDto;
import com.elca.project.entity.User;
import com.elca.project.mapper.UserMapper;
import com.elca.project.repository.RoleRepository;
import com.elca.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        System.out.println(user.getRole());
        var grantedAuthority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getName().toUpperCase());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                List.of(grantedAuthority));
    }

    public User save(UserDto userDto) {
        User newUser = userMapper.userDtoToEntity(userDto);
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(bcryptEncoder.encode(userDto.getPassword()));
        var role = roleRepository.findById(userDto.getRole().getRoleId());
        newUser.setRole(role.get());
        return userRepository.save(newUser);
    }
}
