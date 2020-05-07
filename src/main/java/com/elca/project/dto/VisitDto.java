package com.elca.project.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

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
