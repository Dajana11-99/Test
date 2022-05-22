package com.example.demo.controller;

import com.example.demo.dto.OwnersReportDto;
import com.example.demo.mapper.OwnersReportMapper;
import com.example.demo.model.OwnersReport;
import com.example.demo.service.OwnersReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportsController {
    @Autowired
    private OwnersReportService ownersReportService;
    private OwnersReportMapper ownersReportMapper= new OwnersReportMapper();
    @GetMapping("/getAllReports")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<OwnersReportDto>> getAllReports () {
        Set<OwnersReportDto>reports= new HashSet<>();
        for(OwnersReport ownersReport: ownersReportService.getAllUnApprovedReports())
             reports.add(ownersReportMapper.ownersReportToDto(ownersReport));
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @PostMapping(value= "/sendReviewResponse")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> sendReviewResponse(@RequestBody OwnersReportDto ownersReportDto) {
        ownersReportService.sendReviewResponse(ownersReportDto.getClientUsername(),ownersReportDto.getOwnersUsername(),ownersReportDto.getComment());
        ownersReportService.setReviewStatus(ownersReportDto.getId());
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }




}
