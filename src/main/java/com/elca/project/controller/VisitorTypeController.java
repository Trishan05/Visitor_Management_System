package com.elca.project.controller;

import com.elca.project.dto.VisitorTypeDto;
import com.elca.project.service.VisitorTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/visitortypes")
public class VisitorTypeController {
    VisitorTypeService visitorTypeService;

    public VisitorTypeController(VisitorTypeService visitorTypeService){
        this.visitorTypeService = visitorTypeService;
    }

    @GetMapping
    public ResponseEntity<List<VisitorTypeDto>> getAllVisitorTypes() {
        return new ResponseEntity<>(visitorTypeService.getAllVisitorTypes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VisitorTypeDto> saveVisitorType(@RequestBody VisitorTypeDto visitorTypeDto){
        visitorTypeService.saveVisitorType(visitorTypeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(visitorTypeDto);
    }

    @GetMapping("/{visitorTypeId}")
    public ResponseEntity<VisitorTypeDto> getVisitorTypeById(@PathVariable Long visitorTypeId){
        return new ResponseEntity<>(visitorTypeService.getVisitorTypeById(visitorTypeId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateVisitorType(@RequestBody VisitorTypeDto visitorTypeDto){
        visitorTypeService.updateVisitorType(visitorTypeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
