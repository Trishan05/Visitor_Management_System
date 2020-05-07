package com.elca.project.service.impl;

import com.elca.project.dto.RoleDto;
import com.elca.project.entity.Role;
import com.elca.project.mapper.RoleMapper;
import com.elca.project.repository.RoleRepository;
import com.elca.project.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    final private RoleRepository roleRepository;
    final private RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper){
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    //get by Id
    @Override
    public RoleDto getRoleById(long roleId) {
        Optional<Role> getRoleById = roleRepository.findById(roleId);
        Role role = getRoleById.orElseThrow(null);
        return roleMapper.roleEntityToDto(role);
    }

    //getting all roles
    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::roleEntityToDto).collect(Collectors.toList());
    }

    //inserting role
    @Override
    public void saveRole(RoleDto roleDto) {
        Role newRole = roleMapper.roleDtoToEntity(roleDto);
        roleRepository.save(newRole);
    }

    //update
    @Override
    public void updateRole(RoleDto roleDto) {
        RoleDto roleEntity = getRoleById(roleDto.getRoleId());

        roleEntity.setRoleId(roleDto.getRoleId());
        roleEntity.setName(roleDto.getName());
        roleEntity.setDescription(roleDto.getDescription());
        roleRepository.save(roleMapper.roleDtoToEntity(roleEntity));
    }

    @Override
    public void deleteRole(RoleDto roleDto, long roleId) {

    }

}