package com.elca.project.service.impl;

import com.elca.project.dto.VisitorTypeDto;
import com.elca.project.entity.VisitorType;
import com.elca.project.mapper.VisitorTypeMapper;
import com.elca.project.repository.VisitorTypeRepository;
import com.elca.project.service.VisitorTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitorTypeServiceImpl implements VisitorTypeService {
    final private VisitorTypeRepository visitorTypeRepository;
    final private VisitorTypeMapper visitorTypeMapper;

    public VisitorTypeServiceImpl(VisitorTypeRepository visitorTypeRepository, VisitorTypeMapper visitorTypeMapper){
        this.visitorTypeRepository = visitorTypeRepository;
        this.visitorTypeMapper = visitorTypeMapper;
    }

    // get all visitor
    @Override
    public List<VisitorTypeDto> getAllVisitorTypes() {
        List<VisitorType> visitorTypes = visitorTypeRepository.findAll();
        return visitorTypes.stream().map(visitorTypeMapper::visitorTypeEntityToDto).collect(Collectors.toList());
    }

    //get by Id
    @Override
    public VisitorTypeDto getVisitorTypeById(long visitorTypeId) {
        Optional<VisitorType> getVisitorTypeById = visitorTypeRepository.findById(visitorTypeId);
        VisitorType visitorType = getVisitorTypeById.orElseThrow(null);
        return visitorTypeMapper.visitorTypeEntityToDto(visitorType);
    }

    //inserting visitor
    @Override
    public void saveVisitorType(VisitorTypeDto visitorTypeDto) {
        VisitorType newVisitorType = visitorTypeMapper.visitorTypeDtoToEntity(visitorTypeDto);
        visitorTypeRepository.save(newVisitorType);
    }

    //update
    @Override
    public void updateVisitorType(VisitorTypeDto visitorTypeDto) {
        VisitorTypeDto visitorTypeEntity = getVisitorTypeById(visitorTypeDto.getVisitorTypeId());

        visitorTypeEntity.setVisitorTypeId(visitorTypeDto.getVisitorTypeId());
        visitorTypeEntity.setName(visitorTypeDto.getName());
        visitorTypeEntity.setDescription(visitorTypeDto.getDescription());

        visitorTypeRepository.save(visitorTypeMapper.visitorTypeDtoToEntity(visitorTypeEntity));
    }

}