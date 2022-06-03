package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.AddNewEvaluationDto;
import rs.ac.uns.ftn.isa.fisherman.model.BoatOwnerEvaluation;
import rs.ac.uns.ftn.isa.fisherman.model.BoatReservation;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationBoat;
import rs.ac.uns.ftn.isa.fisherman.repository.BoatOwnerEvaluationRepository;
import rs.ac.uns.ftn.isa.fisherman.service.BoatOwnerEvaluationService;
import rs.ac.uns.ftn.isa.fisherman.service.BoatReservationService;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationBoatService;

import java.time.LocalDateTime;

@Service
public class BoatOwnerEvaluationServiceImpl implements BoatOwnerEvaluationService {

    @Autowired
    private BoatReservationService boatReservationService;
    @Autowired
    private BoatOwnerEvaluationRepository boatOwnerEvaluationRepository;
    @Autowired
    private QuickReservationBoatService quickReservationBoatService;
    @Override
    public void addEvaluation(AddNewEvaluationDto addNewEvaluationDto) {
        if(addNewEvaluationDto.isQuickReservation()){
            QuickReservationBoat boatReservation = quickReservationBoatService.getById(addNewEvaluationDto.getReservationId());
            boatOwnerEvaluationRepository.save(new BoatOwnerEvaluation(null, LocalDateTime.now(), addNewEvaluationDto.getCommentForTheEntityOwner(), addNewEvaluationDto.getGradeForTheEntityOwner(), false, boatReservation.getClient(), boatReservation.getOwnersUsername(), boatReservation.getBoat().getBoatOwner()));
        }else{
            BoatReservation boatReservation = boatReservationService.getById(addNewEvaluationDto.getReservationId());
            boatOwnerEvaluationRepository.save(new BoatOwnerEvaluation(null, LocalDateTime.now(), addNewEvaluationDto.getCommentForTheEntityOwner(), addNewEvaluationDto.getGradeForTheEntityOwner(), false, boatReservation.getClient(), boatReservation.getOwnersUsername(), boatReservation.getBoat().getBoatOwner()));
        }
    }
}
