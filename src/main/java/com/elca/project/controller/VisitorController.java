package com.elca.project.controller;

import com.elca.project.dto.VisitorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.elca.project.service.VisitorService;

import java.util.List;

@RestController
@RequestMapping("/visitors")
public class VisitorController {
    VisitorService visitorService;

    public VisitorController(VisitorService visitorService) { this.visitorService = visitorService;
    }

    @GetMapping
    public ResponseEntity<List<VisitorDto>> getAllVisitors() {
        return new ResponseEntity<>(visitorService.getAllVisitors(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VisitorDto> saveVisitor(@RequestBody VisitorDto visitorDto){
        visitorService.saveVisitor(visitorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(visitorDto);
    }

    @PutMapping
    public ResponseEntity updateVisitor(@RequestBody VisitorDto visitorDto){
        visitorService.updateVisitor(visitorDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{visitorId}")
    public ResponseEntity<VisitorDto> getVisitorById(@PathVariable Long visitorId){
        return new ResponseEntity<>(visitorService.getVisitorById(visitorId), HttpStatus.OK);
    }

    @GetMapping("/specific")
    public ResponseEntity<List<VisitorDto>> getVisitorByFirstnameAndLastname(@RequestParam(name = "fname") String firstName, @RequestParam(name="lname") String lastName){
        return new ResponseEntity<>(visitorService.getVisitorByFirstnameAndLastname(firstName, lastName), HttpStatus.OK);
    }

    @GetMapping("/candidate")
    public ResponseEntity<List<VisitorDto>> getVisitorByCandidate(){
        return new ResponseEntity<>(visitorService.getVisitorByCandidate(), HttpStatus.OK);
    }

    @GetMapping("/status/active")
    public ResponseEntity<List<VisitorDto>> getVisitorByBatchStatus(){
        return new ResponseEntity<>(visitorService.getVisitorByBatchStatus(), HttpStatus.OK);
    }

    @GetMapping("/candidate/active")
    public ResponseEntity<List<VisitorDto>> getVisitorByCandidateAndStatus(){
        return new ResponseEntity<>(visitorService.getVisitorByCandidateAndStatus(),HttpStatus.OK);
    }

//
//    @GetMapping("/export")
//    public ResponseEntity<DownloadDto> getExcel() throws CandidateNotFoundException {
//    CandidatePaginationDto candidatePaginationDto = candidateService.findAllCandidate(PageRequest.of(0, 1000));
//    List<ExcelDto> excelDtos = new ArrayList<>();
//    List<CandidateDto> candidateDtos = candidatePaginationDto.getCandidateDtoList();
//    candidateDtos.forEach(candidateDto -> {
//        candidateDto.getCandidateVenueJobSaveDto().forEach(candidateVenueJobSaveDto -> {
//            VenueJobDto venueJobDto = null;
//            try {
//                venueJobDto = venueJobService.findVenueJobById(candidateVenueJobSaveDto.getVenueJobId());
//            } catch (VenueJobNotFoundException e) {
//                e.printStackTrace();
//            }
//            String venueName = venueJobDto.getVenue().getVenueName();
//            excelDtos.add(new ExcelDto(candidateDto.getRegistrationDate(), candidateDto.getFirstName(), candidateDto.getLastName(), venueName));
//        });
//    });
//
//    var file = ExcelReportView.buildExcelDocument(excelDtos);
//    DownloadDto downloadDto = null;
//    try {
//        downloadDto = DownloadDto.builder().file(Files.readAllBytes(file.toPath())).name("candidates.xls").build();
//    } catch (IOException e) {
//        //log
//    }
//
//    return ResponseEntity.ok(downloadDto);
//    }


}