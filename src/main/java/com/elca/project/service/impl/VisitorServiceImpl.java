package com.elca.project.service.impl;

import com.elca.project.entity.*;
import com.elca.project.mapper.VisitorMapper;
import com.elca.project.repository.CandidateRepository;
import com.elca.project.repository.InterviewRepository;
import com.elca.project.repository.VisitRepository;
import com.elca.project.repository.VisitorRepository;
import com.elca.project.dto.VisitorDto;
import org.springframework.stereotype.Service;
import com.elca.project.service.VisitorService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitorServiceImpl implements VisitorService {
    final private VisitorRepository visitorRepository;
    final private VisitRepository visitRepository;
    final private VisitorMapper visitorMapper;
    final private InterviewRepository interviewRepository;
    final private CandidateRepository candidateRepository;

    public VisitorServiceImpl(VisitorRepository visitorRepository, VisitRepository visitRepository, VisitorMapper visitorMapper, InterviewRepository interviewRepository, CandidateRepository candidateRepository) {
        this.visitorRepository = visitorRepository;
        this.visitRepository = visitRepository;
        this.visitorMapper = visitorMapper;
        this.interviewRepository = interviewRepository;
        this.candidateRepository = candidateRepository;
    }

    //get by Id
    @Override
    public VisitorDto getVisitorById(long visitorId) {
        Optional<Visitor> getVisitorById = visitorRepository.findById(visitorId);
        Visitor visitor = getVisitorById.orElseThrow(null);
        return visitorMapper.visitorEntityToDto(visitor);
        //TODO throw a custom exception+add exception handler
    }


    //get visitor by first and last name
    @Override
    public List<VisitorDto> getVisitorByFirstnameAndLastname(String firstName, String lastName) {
        List<Visitor> getVisitorByFirstnameAndLastname = (List<Visitor>) visitorRepository.findAll(QVisitor.visitor.firstName.eq(firstName)
                .and(QVisitor.visitor.lastName.eq(lastName)));
        return getVisitorByFirstnameAndLastname.stream().map(visitorMapper::visitorEntityToDto).collect(Collectors.toList());
    }


    //get visitor by visitortype name (candidate)
    @Override
    public List<VisitorDto> getVisitorByCandidate() {
        List<Visit> visit = (List<Visit>) visitRepository.findAll(QVisit.visit.visitorType.name.eq("Candidate"));
        List<Visitor> visitors = visit.stream().map(vst -> {
            return visitorRepository.findOne(QVisitor.visitor.visitorId.eq(vst.getVisitor().getVisitorId())).orElse(null);
        }).collect(Collectors.toList());
        return visitors.stream().map(visitorMapper::visitorEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<VisitorDto> getVisitorByCandidateAndStatus() {
        List<Interview> interviews = (List<Interview>) interviewRepository.findAll(QInterview.interview.status.eq("Active"));
        return interviews.stream().map(ivt -> {
            Candidate candidate = candidateRepository
                    .findById(ivt.getCandidate().getCandidateId()).orElse(null);
            return candidate.getVisitor();
        }).map(visitorMapper::visitorEntityToDto).collect(Collectors.toList());
    }


    //get visitor by batchstatus (active)
    @Override
    public List<VisitorDto> getVisitorByBatchStatus() {
        List<Visit> visit = (List<Visit>) visitRepository.findAll(QVisit.visit.badge.status.eq("Active"));
        List<Visitor> visitors = visit.stream().map(vst -> {
            return visitorRepository.findOne(QVisitor.visitor.visitorId.eq(vst.getVisitor().getVisitorId())).orElse(null);
        }).collect(Collectors.toList());
        return visitors.stream().map(visitorMapper::visitorEntityToDto).collect(Collectors.toList());
    }


    //getting all visitor
    @Override
    public List<VisitorDto> getAllVisitors() {
        List<Visitor> visitors = visitorRepository.findAll();
        return visitors.stream().map(visitorMapper::visitorEntityToDto).collect(Collectors.toList());
    }

    //inserting visitor
    @Override
    public Visitor saveVisitor(VisitorDto visitorDto) {
        Visitor newVisitor = visitorMapper.visitorDtoToEntity(visitorDto);
        return visitorRepository.save(newVisitor);
    }

    //update
    @Override
    public void updateVisitor(VisitorDto visitorDto) {
        VisitorDto visitorEntity = getVisitorById(visitorDto.getVisitorId());

        visitorEntity.setVisitorId(visitorDto.getVisitorId());
        visitorEntity.setFirstName(visitorDto.getFirstName());
        visitorEntity.setLastName(visitorDto.getLastName());
        visitorEntity.setEmail(visitorDto.getEmail());
        visitorEntity.setOrganisation(visitorDto.getOrganisation());
        visitorEntity.setPhoneNo(visitorDto.getPhoneNo());
        visitorRepository.save(visitorMapper.visitorDtoToEntity(visitorEntity));
    }

    @Override
    public void deleteVisitor(VisitorDto visitorDto, long visitorId) {

    }

}
