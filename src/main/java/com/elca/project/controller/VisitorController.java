package com.elca.project.controller;

import com.elca.project.dto.VisitorDto;
import com.elca.project.entity.Visitor;
import com.elca.project.exporttoexcel.ExcelGenerator;
import com.elca.project.repository.VisitorRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.elca.project.service.VisitorService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/visitors")
public class VisitorController {
    VisitorService visitorService;
    VisitorRepository visitorRepository;

    public VisitorController(VisitorService visitorService, VisitorRepository visitorRepository) {
        this.visitorService = visitorService;
        this.visitorRepository = visitorRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<VisitorDto>> getAllVisitors(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "visitorId") String sortBy) {

        List<VisitorDto> list = visitorService.getAllVisitors(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<VisitorDto>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE') OR hasRole('ROLE_VISITOR')")
    public ResponseEntity<VisitorDto> saveVisitor(@RequestBody VisitorDto visitorDto){
        visitorService.saveVisitor(visitorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(visitorDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity updateVisitor(@RequestBody VisitorDto visitorDto){
        visitorService.updateVisitor(visitorDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{visitorId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<VisitorDto> getVisitorById(@PathVariable Long visitorId){
        return new ResponseEntity<>(visitorService.getVisitorById(visitorId), HttpStatus.OK);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<VisitorDto>> getVisitorByFirstnameAndLastname(@RequestParam(name = "fname") String firstName, @RequestParam(name="lname") String lastName){
        return new ResponseEntity<>(visitorService.getVisitorByFirstnameAndLastname(firstName, lastName), HttpStatus.OK);
    }

    @GetMapping("/visitortype")
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<VisitorDto>> getVisitorByVisitorType(@RequestParam(name = "vtname") String name){
        return new ResponseEntity<>(visitorService.getVisitorByVisitorType(name), HttpStatus.OK);
    }

    @GetMapping("/badge")
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<VisitorDto>> getVisitorByBadgeStatus(@RequestParam(name = "status") String status){
        return new ResponseEntity<>(visitorService.getVisitorByBadgeStatus(status), HttpStatus.OK);
    }

    @GetMapping("/candidate")
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<VisitorDto>> getVisitorByCandidateAndStatus(@RequestParam(name = "status")String status){
        return new ResponseEntity<>(visitorService.getVisitorByCandidateAndStatus(status),HttpStatus.OK);
    }

    //Export to Excel
    @GetMapping(value = "/download/candidate.xlsx")
//    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<InputStreamResource> excelCandidatesReport() throws IOException {
        List<VisitorDto> visitorC=(List<VisitorDto>) visitorService.getVisitorByActiveCandidate("Active");

        String[] columns = {"Id" , "FirstName", "LastName", "Email", "PhoneNo", "Organisation"};

        String sheetname = "Active Candidate";

        ByteArrayInputStream in = ExcelGenerator.visitorToExcel(visitorC, columns, sheetname);
        // return IOUtils.toByteArray(in);

        HttpHeaders headers = new HttpHeaders();
        //set filename in header
        headers.add("Content-Disposition", "attachment; filename=candidates.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }


//    @GetMapping("/dates")
//    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
//    public ResponseEntity<List<VisitorDto>> findBetweenDates(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
//                                                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {
//
//        if (dateFrom != null && dateTo != null) {
//            return new ResponseEntity<>(visitorService.findBetweenDates(dateFrom, dateTo), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(visitorService.getAllVisitors(), HttpStatus.OK);
//        }
//
//    }

//    @GetMapping("/download")
//    public ResponseEntity<InputStreamResource> excelVisitorReport(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
//                                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) throws Exception{
//        List<VisitorDto> visitors = (List<VisitorDto>) visitorService.findBetweenDates(dateFrom, dateTo);
//
//        ByteArrayInputStream in = ExcelGenerator.visitorToExcel(visitors);
//        // return IOUtils.toByteArray(in);
//
//        HttpHeaders headers = new HttpHeaders();
//        //set filename in header
//        headers.add("Content-Disposition", "attachment; filename=visitors.xlsx");
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .body(new InputStreamResource(in));
//
//
//    }

}