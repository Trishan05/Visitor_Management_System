package com.elca.project.dto;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
public class VisitDto {
    private Long visitId;
    private LocalDate date;
    private LocalTime timeIn;
    private LocalTime timeOut;
    private VisitorDto visitor;
    private VisitorTypeDto visitorType;
    private BadgeDto badge;
}
