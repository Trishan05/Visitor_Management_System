package com.elca.project.controller;

import com.elca.project.dto.UserDto;
import com.elca.project.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) { this.userService=userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
        userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity updateUser(@RequestBody UserDto userDto){
        userService.updateUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }
}
