package com.elca.project.service;

import com.elca.project.dto.VisitDto;
import com.elca.project.dto.VisitorDto;

import java.time.LocalDate;
import java.util.List;

public interface VisitService {
    List<VisitDto> getAllVisits();
    VisitDto getVisitById(long visitId);
    void saveVisit(VisitDto visitDto);
    void updateVisit(VisitDto visitDto);
    void deleteVisit(VisitDto visitDto, long visitId);
    List<VisitorDto> findBetweenDates(LocalDate fromDate, LocalDate toDate);

}
