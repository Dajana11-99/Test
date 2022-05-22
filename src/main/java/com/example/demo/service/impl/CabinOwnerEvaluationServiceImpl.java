package com.example.demo.service.impl;

import com.example.demo.dto.AddNewEvaluationDto;
import com.example.demo.model.CabinOwnerEvaluation;
import com.example.demo.model.CabinReservation;
import com.example.demo.repository.CabinOwnerEvaluationRepository;
import com.example.demo.repository.CabinReservationRepository;
import com.example.demo.service.CabinOwnerEvaluationService;
import com.example.demo.service.CabinService;
import com.example.demo.service.ClientService;
import com.example.demo.service.ReservationCabinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CabinOwnerEvaluationServiceImpl implements CabinOwnerEvaluationService {

    @Autowired
    ClientService clientService;
    @Autowired
    CabinReservationRepository cabinReservationRepository;
    @Autowired
    CabinOwnerEvaluationRepository cabinOwnerEvaluationRepository;
    @Autowired
    CabinService cabinService;
    @Autowired
    ReservationCabinService cabinReservationService;

    @Override
    public void addEvaluation(AddNewEvaluationDto addNewEvaluationDto) {
        CabinReservation cabinReservation = cabinReservationService.getById(addNewEvaluationDto.getReservationId());
        cabinOwnerEvaluationRepository.save(new CabinOwnerEvaluation(null, LocalDateTime.now(), addNewEvaluationDto.getCommentForTheEntityOwner(), addNewEvaluationDto.getGradeForTheEntityOwner(), false, clientService.findByUsername(addNewEvaluationDto.getClientUsername()), cabinReservation.getCabin().getCabinOwner().getUsername(), cabinReservation.getCabin().getCabinOwner()));
    }
}
