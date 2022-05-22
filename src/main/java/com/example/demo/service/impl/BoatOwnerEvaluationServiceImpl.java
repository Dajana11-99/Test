package com.example.demo.service.impl;

import com.example.demo.dto.AddNewEvaluationDto;
import com.example.demo.model.BoatOwnerEvaluation;
import com.example.demo.model.BoatReservation;
import com.example.demo.repository.BoatOwnerEvaluationRepository;
import com.example.demo.service.BoatOwnerEvaluationService;
import com.example.demo.service.BoatReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoatOwnerEvaluationServiceImpl implements BoatOwnerEvaluationService {

    @Autowired
    private BoatReservationService boatReservationService;
    @Autowired
    private BoatOwnerEvaluationRepository boatOwnerEvaluationRepository;
    @Override
    public void addEvaluation(AddNewEvaluationDto addNewEvaluationDto) {
        BoatReservation boatReservation = boatReservationService.getById(addNewEvaluationDto.getReservationId());
        boatOwnerEvaluationRepository.save(new BoatOwnerEvaluation(null, LocalDateTime.now(), addNewEvaluationDto.getCommentForTheEntityOwner(), addNewEvaluationDto.getGradeForTheEntityOwner(), false, boatReservation.getClient(), boatReservation.getOwnersUsername(), boatReservation.getBoat().getBoatOwner()));
    }
}
