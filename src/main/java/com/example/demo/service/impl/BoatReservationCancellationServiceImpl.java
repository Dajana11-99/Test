package com.example.demo.service.impl;

import com.example.demo.dto.BoatReservationDto;
import com.example.demo.model.BoatReservation;
import com.example.demo.model.BoatReservationCancellation;
import com.example.demo.repository.BoatReservationCancellationRepository;
import com.example.demo.repository.BoatReservationRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class BoatReservationCancellationServiceImpl implements BoatReservationCancellationService {

    @Autowired
    private BoatReservationCancellationRepository boatReservationCancellationRepository;
    @Autowired
    private BoatService boatService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BoatReservationRepository boatReservationRepository;
    @Autowired
    private ReservationPaymentService reservationPaymentService;
    @Autowired
    private PenaltyService penaltyService;

    @Override
    public boolean addCancellation(BoatReservationDto boatReservationDto) {
        if(boatReservationDto.getStartDate().minusDays(3).isBefore(LocalDateTime.now()))
            return false;
        BoatReservation boatReservation = boatReservationRepository.getById(boatReservationDto.getId());
        BoatReservationCancellation boatReservationCancellation = new BoatReservationCancellation(null, boatReservation.getClient(), boatReservationDto.getStartDate(), boatReservationDto.getEndDate(), boatReservation.getBoat());
        boatReservation.setAddedAdditionalServices(new HashSet<>());
        reservationPaymentService.resetLoyaltyStatusAfterCancellation(boatReservation.getClient(), boatReservation.getBoat().getBoatOwner());
        boatReservationRepository.save(boatReservation);
        boatReservationRepository.deleteByReservationId(boatReservation.getId());
        boatReservationCancellationRepository.save(boatReservationCancellation);
        penaltyService.addPenalty(boatReservation.getClient().getUsername());
        return true;
    }

    @Override
    public boolean clientHasCancellationForBoatInPeriod(BoatReservationDto boatReservationDto) {
        return boatReservationCancellationRepository.clientHasCancellationForBoatInPeriod(boatService.findById(boatReservationDto.getBoatDto().getId()).getId(), clientService.findByUsername(boatReservationDto.getClientUsername()).getId(), boatReservationDto.getStartDate(), boatReservationDto.getEndDate());
    }
}
