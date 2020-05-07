package com.elca.project.mapper;

import com.elca.project.dto.VisitDto;
import com.elca.project.entity.Visit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitMapper {
    Visit visitDtoToEntity (VisitDto visitDto);
    VisitDto visitEntityToDto (Visit visit);
}
