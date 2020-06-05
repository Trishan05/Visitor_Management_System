package com.elca.project.controller;

import com.elca.project.dto.InterviewDto;
import com.elca.project.service.InterviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviews")
public class InterviewController {
    InterviewService interviewService;

    public InterviewController(InterviewService interviewService) { this.interviewService=interviewService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<InterviewDto>> getAllInterviews() {
        return new ResponseEntity<>(interviewService.getAllInterviews(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<InterviewDto> saveInterview(@RequestBody InterviewDto interviewDto){
        interviewService.saveInterview(interviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(interviewDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity updateInterview(@RequestBody InterviewDto interviewDto){
        interviewService.updateInterview(interviewDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{interviewId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<InterviewDto> getInterviewById(@PathVariable Long interviewId){
        return new ResponseEntity<>(interviewService.getInterviewById(interviewId), HttpStatus.OK);
    }

}
