package com.example.demo.service.impl;

import com.example.demo.dto.AddNewEvaluationDto;
import com.example.demo.model.BoatEvaluation;
import com.example.demo.model.BoatReservation;
import com.example.demo.repository.BoatEvaluationRepository;
import com.example.demo.service.BoatEvaluationService;
import com.example.demo.service.BoatReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoatEvaluationServiceImpl implements BoatEvaluationService {

    @Autowired
    private BoatReservationService boatReservationService;
    @Autowired
    private BoatEvaluationRepository boatEvaluationRepository;

    @Override
    public void addEvaluation(AddNewEvaluationDto addNewEvaluationDto) {
        BoatReservation boatReservation = boatReservationService.getById(addNewEvaluationDto.getReservationId());
        boatEvaluationRepository.save(new BoatEvaluation(null, LocalDateTime.now(), addNewEvaluationDto.getCommentForTheEntity(), addNewEvaluationDto.getGradeForTheEntity(), false, boatReservation.getClient(), boatReservation.getOwnersUsername(), boatReservation.getBoat()));
    }

    @Override
    public BoatEvaluation findById(Long Id) {
        return boatEvaluationRepository.getById(Id);
    }
}
