package com.elca.project.dto;

import lombok.Data;

@Data
public class CandidateDto {
    private Long candidateId;
    private String postApplied;
    private VisitorDto visitor;
}
