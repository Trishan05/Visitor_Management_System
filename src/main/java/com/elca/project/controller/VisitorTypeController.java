package com.elca.project.controller;

import com.elca.project.dto.VisitorTypeDto;
import com.elca.project.service.VisitorTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/visitortypes")
public class VisitorTypeController {
    VisitorTypeService visitorTypeService;

    public VisitorTypeController(VisitorTypeService visitorTypeService){
        this.visitorTypeService = visitorTypeService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<List<VisitorTypeDto>> getAllVisitorTypes() {
        return new ResponseEntity<>(visitorTypeService.getAllVisitorTypes(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<VisitorTypeDto> saveVisitorType(@RequestBody VisitorTypeDto visitorTypeDto){
        visitorTypeService.saveVisitorType(visitorTypeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(visitorTypeDto);
    }

    @GetMapping("/{visitorTypeId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<VisitorTypeDto> getVisitorTypeById(@PathVariable Long visitorTypeId){
        return new ResponseEntity<>(visitorTypeService.getVisitorTypeById(visitorTypeId), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity updateVisitorType(@RequestBody VisitorTypeDto visitorTypeDto){
        visitorTypeService.updateVisitorType(visitorTypeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
