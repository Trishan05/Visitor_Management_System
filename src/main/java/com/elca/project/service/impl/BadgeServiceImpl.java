package com.elca.project.service.impl;

import com.elca.project.dto.BadgeDto;
import com.elca.project.entity.Badge;
import com.elca.project.mapper.BadgeMapper;
import com.elca.project.repository.BadgeRepository;
import com.elca.project.service.BadgeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BadgeServiceImpl implements BadgeService {
    final private BadgeRepository badgeRepository;
    final private BadgeMapper badgeMapper;

    public BadgeServiceImpl(BadgeRepository badgeRepository, BadgeMapper badgeMapper){
        this.badgeRepository = badgeRepository;
        this.badgeMapper = badgeMapper;
    }

    //get by Id
    @Override
    public BadgeDto getBadgeById(long badgeId) {
        Optional<Badge> getBadgeById = badgeRepository.findById(badgeId);
        Badge badge = getBadgeById.orElseThrow(null);
        return badgeMapper.badgeEntityToDto(badge);
    }

    //get by status
    @Override
    public List<BadgeDto> getBadgeByStatus(String status) {
        List<Badge> getBadgeByStatus = badgeRepository.findByStatus(status);
        return getBadgeByStatus.stream().map(badgeMapper::badgeEntityToDto).collect(Collectors.toList());
    }

    //inserting badge
    @Override
    public void saveBadge(BadgeDto badgeDto) {
        Badge newBadge = badgeMapper.badgeDtoToEntity(badgeDto);
        badgeRepository.save(newBadge);
    }

    //getting all badges
    @Override
    public List<BadgeDto> getAllBadges() {
        List<Badge> badges = badgeRepository.findAll();
        return badges.stream().map(badgeMapper::badgeEntityToDto).collect(Collectors.toList());
    }

    //update
    @Override
    public void updateBadge(BadgeDto badgeDto) {
        BadgeDto badgeEntity = getBadgeById(badgeDto.getBadgeId());

        badgeEntity.setBadgeId(badgeDto.getBadgeId());
        badgeEntity.setStatus(badgeDto.getStatus());
        badgeRepository.save(badgeMapper.badgeDtoToEntity(badgeEntity));
    }


    @Override
    public void deleteBadge(BadgeDto badgeDto, long badgeId) {

    }
}
