package com.elca.project.mapper;

import com.elca.project.dto.VisitorTypeDto;
import com.elca.project.entity.VisitorType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitorTypeMapper {
    VisitorType visitorTypeDtoToEntity (VisitorTypeDto visitorTypeDto);
    VisitorTypeDto visitorTypeEntityToDto (VisitorType visitorType);

}
