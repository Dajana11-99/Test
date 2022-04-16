package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.EvaluationDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.EvaluationMapper;
import rs.ac.uns.ftn.isa.fisherman.model.Evaluation;
import rs.ac.uns.ftn.isa.fisherman.service.EvaluationService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/evaluations", produces = MediaType.APPLICATION_JSON_VALUE)
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

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
}