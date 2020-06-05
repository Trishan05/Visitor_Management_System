package com.elca.project.service.impl;

import com.elca.project.dto.VisitDto;
import com.elca.project.entity.QVisit;
import com.elca.project.entity.Visit;
import com.elca.project.mapper.VisitMapper;
import com.elca.project.mapper.VisitorMapper;
import com.elca.project.repository.VisitRepository;
import com.elca.project.repository.VisitorRepository;
import com.elca.project.service.VisitService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl implements VisitService {
    final private VisitRepository visitRepository;
    final private VisitMapper visitMapper;
    final private VisitorRepository visitorRepository;
    final private VisitorMapper visitorMapper;

    public VisitServiceImpl(VisitRepository visitRepository, VisitMapper visitMapper, VisitorRepository visitorRepository, VisitorMapper visitorMapper){
        this.visitRepository = visitRepository;
        this.visitMapper = visitMapper;
        this.visitorRepository = visitorRepository;
        this.visitorMapper = visitorMapper;
    }

    //get by Id
    @Override
    public VisitDto getVisitById(long visitId) {
        Optional<Visit> getVisitById = visitRepository.findById(visitId);
        Visit visit = getVisitById.orElseThrow(null);
        return visitMapper.visitEntityToDto(visit);
    }

    //getting all visit
    @Override
    public List<VisitDto> getAllVisits(Integer pageNo, Integer pageSize, String sortBy){
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

            Page<Visit> pageResult = visitRepository.findAll(paging);

        return pageResult.stream().map(visitMapper::visitEntityToDto).collect(Collectors.toList());
    }

    //inserting visit
    @Override
    public void saveVisit(VisitDto visitDto) {
        Visit newVisit = visitMapper.visitDtoToEntity(visitDto);
        visitRepository.save(newVisit);
    }

    //update
    @Override
    public void updateVisit(VisitDto visitDto) {
        VisitDto visitEntity = getVisitById(visitDto.getVisitId());

        visitEntity.setVisitId(visitDto.getVisitId());
        visitEntity.setDate(visitDto.getDate());
        visitEntity.setTimeIn(visitDto.getTimeIn());
        visitEntity.setTimeOut(visitDto.getTimeOut());
        visitRepository.save(visitMapper.visitDtoToEntity(visitEntity));
    }

    @Override
    public void deleteVisit(VisitDto visitDto, long visitId) {
    }

    @Override
    public List<VisitDto> findBetweenDates(LocalDate fromDate, LocalDate toDate) {
        List<Visit> visit = (List<Visit>) visitRepository.findAll(QVisit.visit.date.between(fromDate, toDate));
        return visit.stream().map(visitMapper::visitEntityToDto).collect(Collectors.toList());
    }

    //get between 2 dates

}