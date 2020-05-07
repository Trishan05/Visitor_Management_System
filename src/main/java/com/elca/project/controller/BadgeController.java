package com.elca.project.controller;

import com.elca.project.dto.BadgeDto;
import com.elca.project.service.BadgeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/badges")
public class BadgeController {
    BadgeService badgeService;

    public BadgeController(BadgeService badgeService){
        this.badgeService = badgeService;
    }

    @GetMapping
    public ResponseEntity<List<BadgeDto>> getAllBadge() {
        return new ResponseEntity<>(badgeService.getAllBadges(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BadgeDto> saveBadge(@RequestBody BadgeDto badgeDto){
        badgeService.saveBadge(badgeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(badgeDto);
    }

    @PutMapping
    public ResponseEntity updateBadge(@RequestBody BadgeDto badgeDto){
        badgeService.updateBadge(badgeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{badgeId}")
    public ResponseEntity<BadgeDto> getBadgeById(@PathVariable Long badgeId){
        return new ResponseEntity<>(badgeService.getBadgeById(badgeId), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<BadgeDto>> getBadgeByStatus(@PathVariable String status){
        return new ResponseEntity<>(badgeService.getBadgeByStatus(status), HttpStatus.OK);
    }

}
