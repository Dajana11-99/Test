package com.example.demo.service.impl;

import com.example.demo.dto.AddNewEvaluationDto;
import com.example.demo.model.CabinEvaluation;
import com.example.demo.model.CabinReservation;
import com.example.demo.repository.CabinEvaluationRepository;
import com.example.demo.repository.CabinReservationRepository;
import com.example.demo.service.CabinEvaluationService;
import com.example.demo.service.CabinService;
import com.example.demo.service.ClientService;
import com.example.demo.service.ReservationCabinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class CabinEvaluationServiceImpl implements CabinEvaluationService {

    @Autowired
    ClientService clientService;
    @Autowired
    CabinReservationRepository cabinReservationRepository;
    @Autowired
    CabinEvaluationRepository cabinEvaluationRepository;
    @Autowired
    CabinService cabinService;
    @Autowired
    ReservationCabinService cabinReservationService;

    @Override
    public void addEvaluation(AddNewEvaluationDto addNewEvaluationDto) {
        CabinReservation cabinReservation = cabinReservationService.getById(addNewEvaluationDto.getReservationId());
        cabinEvaluationRepository.save(new CabinEvaluation(null, LocalDateTime.now(), addNewEvaluationDto.getCommentForTheEntity(), addNewEvaluationDto.getGradeForTheEntity(), false, clientService.findByUsername(addNewEvaluationDto.getClientUsername()), cabinReservation.getCabin().getCabinOwner().getUsername(), cabinReservation.getCabin()));

    }

    @Override
    public Set<CabinEvaluation> findByCabinId(Long cabinId) {
        return cabinEvaluationRepository.findByCabinId(cabinId);
    }

    @Override
    public CabinEvaluation getById(Long id) {
       return cabinEvaluationRepository.getById(id);
    }
}
