package com.example.demo.controller;

import com.example.demo.dto.EvaluationDto;
import com.example.demo.mapper.EvaluationMapper;
import com.example.demo.model.Evaluation;
import com.example.demo.service.EvaluationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/evaluations", produces = MediaType.APPLICATION_JSON_VALUE)
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private UserService userService;

    private EvaluationMapper evaluationMapper= new EvaluationMapper();
    @GetMapping("/getAllEvaluations")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EvaluationDto>> getAllReports() {
        List<EvaluationDto> evaluations = new ArrayList<>();

        for(Evaluation evaluation : evaluationService.getAll())
            evaluations.add(evaluationMapper.evaluationToDto(evaluation));

        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }

    @GetMapping("/setEvaluationStatus/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> setEvaluationStatus(@PathVariable("id")Long id) {
        evaluationService.setEvaluationStatus(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/unapproved/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUnapprovedEvaluation(@PathVariable("id")Long id) {
        evaluationService.deleteUnapprovedEvaluation(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/cabinOwner/{username:.+}/")
    @PreAuthorize("hasRole('CABINOWNER') || hasRole('CLIENT')")
    public ResponseEntity<List<EvaluationDto>> getCabinOwnerEvaluations(@PathVariable("username")String username) {
        List<EvaluationDto> evaluations=new ArrayList<>();
        for(Evaluation evaluation : evaluationService.findCabinOwnerEvaluations(userService.findByUsername(username).getId()))
            evaluations.add(evaluationMapper.evaluationToDto(evaluation));
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }
    @GetMapping("/boatOwner/{username:.+}/")
    @PreAuthorize("hasRole('BOATOWNER') || hasRole('CLIENT')")
    public ResponseEntity<List<EvaluationDto>> getBoatOwnerEvaluations(@PathVariable("username")String username) {
        List<EvaluationDto> evaluations=new ArrayList<>();
        for(Evaluation evaluation : evaluationService.findBoatOwnerEvaluations(userService.findByUsername(username).getId()))
            evaluations.add(evaluationMapper.evaluationToDto(evaluation));
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }
    @GetMapping("/instructor/{username:.+}/")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR') || hasRole('CLIENT')")
    public ResponseEntity<List<EvaluationDto>> getInstructorEvaluations(@PathVariable("username")String username) {
        List<EvaluationDto> evaluations=new ArrayList<>();
        for(Evaluation evaluation : evaluationService.findInstructorEvaluations(userService.findByUsername(username).getId()))
            evaluations.add(evaluationMapper.evaluationToDto(evaluation));
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }
    @GetMapping("/boat/{id}")
    @PreAuthorize("hasRole('BOATOWNER') || hasRole('CLIENT')")
    public ResponseEntity<List<EvaluationDto>> getBoatEvaluations(@PathVariable("id")Long boatId) {
        List<EvaluationDto> evaluations = new ArrayList<>();

        for(Evaluation evaluation : evaluationService.getBoatEvaluations(boatId))
            evaluations.add(evaluationMapper.evaluationToDto(evaluation));

        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }

}