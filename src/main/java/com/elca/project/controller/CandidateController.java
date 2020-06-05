package com.elca.project.controller;

import com.elca.project.dto.CandidateDto;
import com.elca.project.dto.InterviewDto;
import com.elca.project.service.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {
    CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<List<CandidateDto>> getAllCandidates() {
        return new ResponseEntity<>(candidateService.getAllCandidates(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<CandidateDto> saveCandidate(@RequestBody CandidateDto candidateDto) {
        candidateService.saveCandidate(candidateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")

    public ResponseEntity updateCandidate(@RequestBody CandidateDto candidateDto) {
        candidateService.updateCandidate(candidateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{candidateId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<CandidateDto> getCandidateById(@PathVariable Long candidateId) {
        return new ResponseEntity<>(candidateService.getCandidateById(candidateId), HttpStatus.OK);
    }

    @GetMapping("/name")
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<InterviewDto>> getInterviewByCandidateFirstNameAndLastName(@RequestParam(name = "fname") String firstName, @RequestParam(name="lname") String lastName){
        return new ResponseEntity<>(candidateService.getInterviewByCandidateFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
    }
}
