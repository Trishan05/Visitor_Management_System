package com.elca.project.dto;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

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
