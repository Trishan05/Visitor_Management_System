package com.elca.project.mapper;

import com.elca.project.dto.VisitorDto;
import com.elca.project.entity.Visitor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitorMapper {
    Visitor visitorDtoToEntity (VisitorDto visitorDto);
    VisitorDto visitorEntityToDto (Visitor visitor);
}
