package com.elca.project.service;

import com.elca.project.dto.VisitorDto;
import com.elca.project.entity.Visitor;

import java.util.List;

public interface VisitorService {
    VisitorDto getVisitorById(long visitorId);
    List<VisitorDto> getAllVisitors();
    Visitor saveVisitor(VisitorDto visitorDto);
    void updateVisitor(VisitorDto visitorDto);
    void deleteVisitor(VisitorDto visitorDto, long visitorId);
    List<VisitorDto> getVisitorByFirstnameAndLastname(String firstName, String lastName);
    List<VisitorDto> getVisitorByCandidate();
    List<VisitorDto> getVisitorByBatchStatus();
    List<VisitorDto> getVisitorByCandidateAndStatus();

}
