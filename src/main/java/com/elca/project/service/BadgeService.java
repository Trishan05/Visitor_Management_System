package com.elca.project.service;

import com.elca.project.dto.BadgeDto;
import com.elca.project.dto.CandidateDto;

import java.util.List;

public interface BadgeService {
    BadgeDto getBadgeById (long badgeId);
    List<BadgeDto> getAllBadges();
    void saveBadge(BadgeDto badgeDto);
    void updateBadge(BadgeDto badgeDto);
    void deleteBadge(BadgeDto badgeDto, long badgeId);
    List<BadgeDto> getBadgeByStatus(String status);
}
