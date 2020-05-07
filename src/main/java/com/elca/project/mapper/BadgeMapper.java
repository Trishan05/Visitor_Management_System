package com.elca.project.mapper;

import com.elca.project.dto.BadgeDto;
import com.elca.project.entity.Badge;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BadgeMapper {
    Badge badgeDtoToEntity(BadgeDto badgeDto);
    BadgeDto badgeEntityToDto(Badge badge);
}
