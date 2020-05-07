package com.elca.project.service;

import com.elca.project.dto.RoleDto;
import com.elca.project.dto.UserDto;

import java.util.List;

public interface RoleService {
    RoleDto getRoleById(long roleId);
    List<RoleDto> getAllRoles();
    void saveRole(RoleDto roleDto);
    void updateRole(RoleDto roleDto);
    void deleteRole(RoleDto roleDto, long roleId);
}
