package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.AddNewEvaluationDto;
import rs.ac.uns.ftn.isa.fisherman.service.BoatEvaluationService;
import rs.ac.uns.ftn.isa.fisherman.service.BoatOwnerEvaluationService;
import rs.ac.uns.ftn.isa.fisherman.service.BoatReservationService;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationBoatService;

@RestController
@RequestMapping(value = "/boatEvaluation", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoatEvaluationController {

    @Autowired
    private BoatReservationService boatReservationService;
    @Autowired
    private BoatEvaluationService boatEvaluationService;
    @Autowired
    private QuickReservationBoatService quickReservationBoatService;
    @Autowired
    private BoatOwnerEvaluationService boatOwnerEvaluationService;


    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/addEvaluation")
    public ResponseEntity<String> addEvaluation(@RequestBody AddNewEvaluationDto addNewEvaluationDto){
        if(!addNewEvaluationDto.isQuickReservation() && boatReservationService.checkIfReservationIsEvaluated(addNewEvaluationDto.getReservationId())) {
            return new ResponseEntity<>("Reservation already has evaluations!", HttpStatus.BAD_REQUEST);
        }
        if(addNewEvaluationDto.isQuickReservation() && quickReservationBoatService.checkIfReservationIsEvaluated(addNewEvaluationDto.getReservationId())){
            return new ResponseEntity<>("Quick reservation already has evaluations!", HttpStatus.BAD_REQUEST);
        }
        boatEvaluationService.addEvaluation(addNewEvaluationDto);
        boatOwnerEvaluationService.addEvaluation(addNewEvaluationDto);
        if(!addNewEvaluationDto.isQuickReservation())
            boatReservationService.markThatReservationIsEvaluated(addNewEvaluationDto.getReservationId());
        else
            quickReservationBoatService.markThatReservationIsEvaluated(addNewEvaluationDto.getReservationId());
        return new ResponseEntity<>("Evaluations successfully added.", HttpStatus.OK);
    }

}
