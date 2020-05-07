package com.elca.project.service;

import com.elca.project.dto.InterviewDto;

import java.util.List;

public interface InterviewService {

    InterviewDto getInterviewById (long interviewId);
    List<InterviewDto> getAllInterviews();
    void saveInterview(InterviewDto interviewDto);
    void updateInterview(InterviewDto interviewDto);
    void deleteInterview(InterviewDto interviewDto, long interviewId);
}
