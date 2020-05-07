package com.elca.project.service;

import com.elca.project.dto.CandidateDto;
import com.elca.project.dto.InterviewDto;

import java.util.List;

public interface CandidateService {
    CandidateDto getCandidateById (long candidateId);
    List<CandidateDto> getAllCandidates();
    void saveCandidate(CandidateDto candidateDto);
    void updateCandidate(CandidateDto candidateDto);
    void deleteCandidate(CandidateDto candidateDto, long candidateId);
    List<InterviewDto> getInterviewByCandidateFirstNameAndLastName(String firstName, String lastName);
}
