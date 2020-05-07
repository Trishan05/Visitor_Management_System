package com.elca.project.mapper;

import com.elca.project.dto.RoleDto;
import com.elca.project.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role roleDtoToEntity (RoleDto roleDto);
    RoleDto roleEntityToDto(Role role);
}
