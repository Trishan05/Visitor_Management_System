package com.elca.project.controller;

import com.elca.project.dto.RoleDto;
import com.elca.project.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleDto> saveRole(@RequestBody RoleDto roleDto){
        roleService.saveRole(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleDto);
    }

    @PutMapping
    public ResponseEntity updateRole(@RequestBody RoleDto roleDto){
        roleService.updateRole(roleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long roleId){
        return new ResponseEntity<>(roleService.getRoleById(roleId), HttpStatus.OK);
    }

}
