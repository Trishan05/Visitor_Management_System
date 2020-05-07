package com.elca.project.service.impl;

import com.elca.project.dto.CandidateDto;
import com.elca.project.dto.InterviewDto;
import com.elca.project.entity.*;
import com.elca.project.mapper.CandidateMapper;
import com.elca.project.mapper.InterviewMapper;
import com.elca.project.repository.CandidateRepository;
import com.elca.project.repository.InterviewRepository;
import com.elca.project.repository.VisitorRepository;
import com.elca.project.service.CandidateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {
    final private CandidateRepository candidateRepository;
    final private CandidateMapper candidateMapper;
    final private InterviewMapper interviewMapper;
    final private InterviewRepository interviewRepository;
    private final VisitorRepository visitorRepository;
    public CandidateServiceImpl(CandidateRepository candidateRepository, CandidateMapper candidateMapper, InterviewMapper interviewMapper, InterviewRepository interviewRepository, VisitorRepository visitorRepository) {
        this.candidateRepository = candidateRepository;
        this.candidateMapper = candidateMapper;
        this.interviewMapper = interviewMapper;
        this.interviewRepository = interviewRepository;
        this.visitorRepository = visitorRepository;
    }

    //get by Id
    @Override
    public CandidateDto getCandidateById(long candidateId) {
        Optional<Candidate> getCandidateById = candidateRepository.findById(candidateId);
        Candidate candidate = getCandidateById.orElseThrow(null);
        return candidateMapper.candidateEntityToDto(candidate);
    }

    //inserting candidate
    @Override
    public void saveCandidate(CandidateDto candidateDto) {
        Candidate newCandidate = candidateMapper.candidateDtoToEntity(candidateDto);
        candidateRepository.save(newCandidate);
    }

    //getting all candidates
    @Override
    public List<CandidateDto> getAllCandidates() {
        List<Candidate> candidates = candidateRepository.findAll();
        return candidates.stream().map(candidateMapper::candidateEntityToDto).collect(Collectors.toList());
    }

    //update
    @Override
    public void updateCandidate(CandidateDto candidateDto) {
        CandidateDto candidateEntity = getCandidateById(candidateDto.getCandidateId());

        candidateEntity.setCandidateId(candidateDto.getCandidateId());
        candidateEntity.setPostApplied(candidateDto.getPostApplied());
        candidateRepository.save(candidateMapper.candidateDtoToEntity(candidateEntity));
    }


    @Override
    public void deleteCandidate(CandidateDto candidateDto, long candidateId) {

    }

    public List<InterviewDto> getInterviewByCandidateFirstNameAndLastName(String firstName, String lastName){
        List<Visitor> visitors = (List<Visitor>) visitorRepository.findAll(QVisitor.visitor.firstName.eq(firstName)
        .and(QVisitor.visitor.lastName.eq(lastName)));

        List<Long> candidates = visitors.stream().map(vst -> {
            Candidate cdt = candidateRepository.findOne(QCandidate.candidate.visitor.visitorId.eq(vst.getVisitorId())).orElse(null);
            return cdt.getCandidateId();
        }).collect(Collectors.toList());

        return candidates.stream().map(candidateId -> {
            return interviewRepository.findOne(QInterview.interview.candidate.candidateId.eq(candidateId)).orElse(null);
        }).map(interviewMapper::interviewEntityToDto).collect(Collectors.toList());
    }

}

