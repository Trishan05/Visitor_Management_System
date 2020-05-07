package com.elca.project.controller;

import com.elca.project.controller.model.Date;
import com.elca.project.dto.VisitDto;
import com.elca.project.dto.VisitorDto;
import com.elca.project.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/visits")
public class VisitController {
    VisitService visitService;

    public VisitController(VisitService visitService){
        this.visitService = visitService;
    }

    @GetMapping
    public ResponseEntity<List<VisitDto>> getAllVisits() {
        return new ResponseEntity<>(visitService.getAllVisits(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VisitDto> saveVisit(@RequestBody VisitDto visitDto){
        visitService.saveVisit(visitDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(visitDto);
    }

    @PutMapping
    public ResponseEntity updateVisit(@RequestBody VisitDto visitDto){
        visitService.updateVisit(visitDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{visitId}")
    public ResponseEntity<VisitDto> getVisitById(@PathVariable Long visitId){
        return new ResponseEntity<>(visitService.getVisitById(visitId), HttpStatus.OK);
    }

    @PostMapping("/dates")
    public ResponseEntity<List<VisitorDto>> findBetweenDates(@RequestBody Date date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromDate = LocalDate.parse(date.getFromDate(), formatter);
        LocalDate toDate = LocalDate.parse(date.getToDate(), formatter);
        return new ResponseEntity<>(visitService.findBetweenDates(fromDate, toDate), HttpStatus.OK);
    }

}
