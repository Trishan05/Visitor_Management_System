package com.elca.project.mapper;

import com.elca.project.dto.CandidateDto;
import com.elca.project.entity.Candidate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    Candidate candidateDtoToEntity(CandidateDto candidateDto);
    CandidateDto candidateEntityToDto (Candidate candidate);

}