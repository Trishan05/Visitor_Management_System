package com.elca.project.service.impl;

import com.elca.project.dto.CandidateDto;
import com.elca.project.entity.Candidate;
import com.elca.project.entity.Interview;
import com.elca.project.entity.QInterview;
import com.elca.project.mapper.CandidateMapper;
import com.elca.project.mapper.InterviewMapper;
import com.elca.project.repository.CandidateRepository;
import com.elca.project.repository.InterviewRepository;
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

    public CandidateServiceImpl(CandidateRepository candidateRepository, CandidateMapper candidateMapper, InterviewMapper interviewMapper, InterviewRepository interviewRepository) {
        this.candidateRepository = candidateRepository;
        this.candidateMapper = candidateMapper;
        this.interviewMapper = interviewMapper;
        this.interviewRepository = interviewRepository;
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

    @Override
    public List<CandidateDto> getCandidateByStatus() {

        List<Interview> interview = (List<Interview>) interviewRepository.findAll(QInterview.interview.status.eq("Active"));
        List<Candidate> candidates = interview.stream().map(ivt -> {
            return candidateRepository.findById(ivt.getCandidate().getCandidateId()).orElse(null);
        }).collect(Collectors.toList());

        return candidates.stream().map(candidate -> {
            return candidateMapper.candidateEntityToDto(candidate);
        }).collect(Collectors.toList());
    }


}
