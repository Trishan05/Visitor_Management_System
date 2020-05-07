package com.elca.project.service;

import com.elca.project.dto.VisitorTypeDto;

import java.util.List;

public interface VisitorTypeService {
    List<VisitorTypeDto> getAllVisitorTypes();
    VisitorTypeDto getVisitorTypeById(long visitorTypeId);
    void saveVisitorType(VisitorTypeDto visitorTypeDto);
    void updateVisitorType(VisitorTypeDto visitorTypeDto);
}
