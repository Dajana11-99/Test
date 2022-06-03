package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.AddNewFishingInstructorEvaluationDto;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.repository.FishingInstructorEvaluationRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AdventureReservationService;
import rs.ac.uns.ftn.isa.fisherman.service.FishingInstructorEvaluationService;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationAdventureService;

import java.time.LocalDateTime;

@Service
public class FishingInstructorEvaluationServiceImpl implements FishingInstructorEvaluationService {

    @Autowired
    private AdventureReservationService adventureReservationService;
    @Autowired
    private QuickReservationAdventureService quickReservationAdventureService;
    @Autowired
    private FishingInstructorEvaluationRepository fishingInstructorEvaluationRepository;
    @Override
    public void addEvaluation(AddNewFishingInstructorEvaluationDto addNewFishingInstructorEvaluationDto) {
        Client client;
        FishingInstructor fishingInstructor;
        if(addNewFishingInstructorEvaluationDto.isQuickReservation()) {
            QuickReservationAdventure quickReservationAdventure = quickReservationAdventureService.findById(addNewFishingInstructorEvaluationDto.getReservationId());
            client = quickReservationAdventure.getClient();
            fishingInstructor = quickReservationAdventure.getFishingInstructor();
        }else{
            AdventureReservation adventureReservation = adventureReservationService.findById(addNewFishingInstructorEvaluationDto.getReservationId());
            client = adventureReservation.getClient();
            fishingInstructor = adventureReservation.getFishingInstructor();
        }
        fishingInstructorEvaluationRepository.save(new FishingInstructorEvaluation(null, LocalDateTime.now(), addNewFishingInstructorEvaluationDto.getCommentForTheFishingInstructor(), addNewFishingInstructorEvaluationDto.getGradeForTheFishingInstructor(), false, client, fishingInstructor.getUsername()));
    }
}
