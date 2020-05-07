package com.elca.project.mapper;

import com.elca.project.dto.InterviewDto;
import com.elca.project.entity.Interview;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface InterviewMapper {
    Interview interviewDtoToEntity(InterviewDto interviewDto);
    InterviewDto interviewEntityToDto(Interview interview);

}
