package com.elca.project.service;

import com.elca.project.dto.VisitorDto;
import com.elca.project.entity.Visitor;

import java.time.LocalDate;
import java.util.List;

public interface VisitorService {
    VisitorDto getVisitorById(long visitorId);

    List<VisitorDto> getAllVisitors(Integer pageNo, Integer pageSize, String sortBy);

    Visitor saveVisitor(VisitorDto visitorDto);

    void updateVisitor(VisitorDto visitorDto);

    void deleteVisitor(VisitorDto visitorDto, long visitorId);

    List<VisitorDto> getVisitorByFirstnameAndLastname(String firstName, String lastName);

    List<VisitorDto> getVisitorByVisitorType(String name);

    List<VisitorDto> getVisitorByBadgeStatus(String status);

    List<VisitorDto> getVisitorByCandidateAndStatus(String status);

    List<VisitorDto> getVisitorByActiveCandidate(String status);

//    List<VisitorDto> findBetweenDates(LocalDate dateFrom, LocalDate dateTo);

}
