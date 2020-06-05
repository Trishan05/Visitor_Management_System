package com.elca.project.controller;

//import com.elca.project.controller.model.Date;
import com.elca.project.dto.VisitDto;
import com.elca.project.dto.VisitorDto;
import com.elca.project.entity.Interview;
import com.elca.project.entity.Visit;
import com.elca.project.exporttoexcel.ExcelGenerator;
import com.elca.project.exporttoexcel.VisitExcelGenerator;
import com.elca.project.service.VisitService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {
    VisitService visitService;

    public VisitController(VisitService visitService){
        this.visitService = visitService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<List<VisitDto>> getAllVisits(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "visitId") String sortBy)
    {
        List<VisitDto> list = visitService.getAllVisits(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<VisitDto>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<VisitDto> saveVisit(@RequestBody VisitDto visitDto){
        visitService.saveVisit(visitDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(visitDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity updateVisit(@RequestBody VisitDto visitDto){
        visitService.updateVisit(visitDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{visitId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<VisitDto> getVisitById(@PathVariable Long visitId){
        return new ResponseEntity<>(visitService.getVisitById(visitId), HttpStatus.OK);
    }

    @GetMapping("/dates")
    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<VisitDto>> findBetweenDates(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {

//        if (dateFrom != null && dateTo != null) {
            return new ResponseEntity<>(visitService.findBetweenDates(dateFrom, dateTo), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(visitService.getAllVisits(), HttpStatus.OK);
//        }

    }

    @GetMapping("/download")
//    @PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_HR') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<InputStreamResource> excelVisitReport(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) throws Exception{
        List<VisitDto> visits = (List<VisitDto>) visitService.findBetweenDates(dateFrom, dateTo);

        ByteArrayInputStream in = VisitExcelGenerator.visitToExcel(visits);
        // return IOUtils.toByteArray(in);

        HttpHeaders headers = new HttpHeaders();
        //set filename in header
        headers.add("Content-Disposition", "attachment; filename=visits.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));


    }

}
