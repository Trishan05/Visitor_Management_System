package com.elca.project.service.impl;

import com.elca.project.dto.InterviewDto;
import com.elca.project.entity.Interview;
import com.elca.project.mapper.InterviewMapper;
import com.elca.project.repository.InterviewRepository;
import com.elca.project.service.InterviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InterviewServiceImpl implements InterviewService {

    final private InterviewRepository interviewRepository;
    final private InterviewMapper interviewMapper;

    public InterviewServiceImpl(InterviewRepository interviewRepository, InterviewMapper interviewMapper) {
        this.interviewRepository=interviewRepository;
        this.interviewMapper=interviewMapper;
    }

    //get by Id
    @Override
    public InterviewDto getInterviewById(long interviewId) {
        Optional<Interview> getInterviewById = interviewRepository.findById(interviewId);
        Interview interview = getInterviewById.orElseThrow(null);
        return interviewMapper.interviewEntityToDto(interview);
    }

    //getting all interviews
    @Override
    public List<InterviewDto> getAllInterviews() {
        List<Interview> interviews = interviewRepository.findAll();
        return interviews.stream().map(interviewMapper::interviewEntityToDto).collect(Collectors.toList());
    }

    //inserting interview
    @Override
    public void saveInterview(InterviewDto interviewDto) {
        Interview newInterview = interviewMapper.interviewDtoToEntity(interviewDto);
        interviewRepository.save(newInterview);
    }

    //update
    @Override
    public void updateInterview(InterviewDto interviewDto) {
        InterviewDto interviewEntity = getInterviewById(interviewDto.getInterviewId());

        interviewEntity.setInterviewId(interviewDto.getInterviewId());
        interviewEntity.setInterviewer(interviewDto.getInterviewer());
        interviewEntity.setDate(interviewDto.getDate());
        interviewEntity.setTime(interviewDto.getTime());
        interviewEntity.setStatus(interviewDto.getStatus());
        interviewEntity.setStep(interviewDto.getStep());
        interviewRepository.save(interviewMapper.interviewDtoToEntity(interviewEntity));
    }

    @Override
    public void deleteInterview(InterviewDto interviewDto, long interviewId) {

    }

}
