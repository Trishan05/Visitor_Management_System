package com.elca.project.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class InterviewDto {
    private Long interviewId;
    private String interviewer;
    private LocalDate date;
    private LocalTime time;
    private String status;
    private String step;
    private CandidateDto candidate;
}
