package com.elca.project.controller;

import com.elca.project.dto.BadgeDto;
import com.elca.project.service.BadgeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/badges")
public class BadgeController {
    BadgeService badgeService;

    public BadgeController(BadgeService badgeService){
        this.badgeService = badgeService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<List<BadgeDto>> getAllBadge() {
        return new ResponseEntity<>(badgeService.getAllBadges(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<BadgeDto> saveBadge(@RequestBody BadgeDto badgeDto){
        badgeService.saveBadge(badgeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(badgeDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity updateBadge(@RequestBody BadgeDto badgeDto){
        badgeService.updateBadge(badgeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{badgeId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<BadgeDto> getBadgeById(@PathVariable Long badgeId){
        return new ResponseEntity<>(badgeService.getBadgeById(badgeId), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<List<BadgeDto>> getBadgeByStatus(@PathVariable String status){
        return new ResponseEntity<>(badgeService.getBadgeByStatus(status), HttpStatus.OK);
    }

}
