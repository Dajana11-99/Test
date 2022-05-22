package com.example.demo.controller;

import com.example.demo.dto.AddNewEvaluationDto;
import com.example.demo.service.BoatEvaluationService;
import com.example.demo.service.BoatOwnerEvaluationService;
import com.example.demo.service.BoatReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/boatEvaluation", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoatEvaluationController {

    @Autowired
    private BoatReservationService boatReservationService;
    @Autowired
    private BoatEvaluationService boatEvaluationService;
    @Autowired
    private BoatOwnerEvaluationService boatOwnerEvaluationService;


    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/addEvaluation")
    public ResponseEntity<String> addEvaluation(@RequestBody AddNewEvaluationDto addNewEvaluationDto){
        if(!boatReservationService.checkIfReservationIsEvaluated(addNewEvaluationDto.getReservationId())){
            boatEvaluationService.addEvaluation(addNewEvaluationDto);
            boatOwnerEvaluationService.addEvaluation(addNewEvaluationDto);
            boatReservationService.markThatReservationIsEvaluated(addNewEvaluationDto.getReservationId());
            return new ResponseEntity<>("Evaluations successfully added.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Reservation already has evaluations!", HttpStatus.BAD_REQUEST);
        }
    }

}
