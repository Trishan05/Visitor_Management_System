package com.elca.project.controller;

import com.elca.project.dto.CandidateDto;
import com.elca.project.dto.InterviewDto;
import com.elca.project.dto.VisitorDto;
import com.elca.project.service.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/candidates")
public class CandidateController {
    CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<List<CandidateDto>> getAllCandidates() {
        return new ResponseEntity<>(candidateService.getAllCandidates(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CandidateDto> saveCandidate(@RequestBody CandidateDto candidateDto) {
        candidateService.saveCandidate(candidateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateDto);
    }

    @PutMapping
    public ResponseEntity updateCandidate(@RequestBody CandidateDto candidateDto) {
        candidateService.updateCandidate(candidateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<CandidateDto> getCandidateById(@PathVariable Long candidateId) {
        return new ResponseEntity<>(candidateService.getCandidateById(candidateId), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<InterviewDto>> getInterviewByCandidateFirstNameAndLastName(@RequestParam(name = "fname") String firstName, @RequestParam(name="lname") String lastName){
        return new ResponseEntity<>(candidateService.getInterviewByCandidateFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
    }
}
