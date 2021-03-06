package com.elca.project.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String username;
    private String password;
    private RoleDto role;
}
