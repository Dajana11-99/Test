package com.example.demo.service.impl;

import com.example.demo.dto.AddNewFishingInstructorEvaluationDto;
import com.example.demo.model.AdventureReservation;
import com.example.demo.model.FishingInstructorEvaluation;
import com.example.demo.repository.FishingInstructorEvaluationRepository;
import com.example.demo.service.AdventureReservationService;
import com.example.demo.service.FishingInstructorEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FishingInstructorEvaluationServiceImpl implements FishingInstructorEvaluationService {

    @Autowired
    private AdventureReservationService adventureReservationService;
    @Autowired
    private FishingInstructorEvaluationRepository fishingInstructorEvaluationRepository;
    @Override
    public void addEvaluation(AddNewFishingInstructorEvaluationDto addNewFishingInstructorEvaluationDto) {
        AdventureReservation adventureReservation = adventureReservationService.findById(addNewFishingInstructorEvaluationDto.getReservationId());
        fishingInstructorEvaluationRepository.save(new FishingInstructorEvaluation(null, LocalDateTime.now(), addNewFishingInstructorEvaluationDto.getCommentForTheFishingInstructor(), addNewFishingInstructorEvaluationDto.getGradeForTheFishingInstructor(), false, adventureReservation.getClient(), adventureReservation.getFishingInstructor().getUsername()));
    }
}
