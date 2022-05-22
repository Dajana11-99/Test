package com.example.demo.service.impl;

import com.example.demo.dto.AdventureReservationDto;
import com.example.demo.model.AdventureReservation;
import com.example.demo.model.AdventureReservationCancellation;
import com.example.demo.repository.AdventureReservationCancellationRepository;
import com.example.demo.repository.AdventureReservationRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AdventureReservationCancellationImpl implements AdventureReservationCancellationService {

    @Autowired
    private AdventureReservationCancellationRepository adventureReservationCancellationRepository;
    @Autowired
    private PenaltyService penaltyService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private FishingInstructorService fishingInstructorService;
    @Autowired
    private AdventureReservationRepository adventureReservationRepository;
    @Autowired
    private ReservationPaymentService reservationPaymentService;
    @Override
    public boolean addCancellation(AdventureReservationDto adventureReservationDto) {
        AdventureReservation adventureReservation = adventureReservationRepository.getById(adventureReservationDto.getId());
        AdventureReservationCancellation adventureReservationCancellation = new AdventureReservationCancellation(null, adventureReservation.getClient(), adventureReservation.getStartDate(), adventureReservation.getEndDate(), adventureReservation.getFishingInstructor());
        adventureReservation.setAddedAdditionalServices(new HashSet<>());
        reservationPaymentService.resetLoyaltyStatusAfterCancellation(adventureReservation.getClient(), adventureReservation.getFishingInstructor());
        adventureReservationRepository.save(adventureReservation);
        adventureReservationRepository.deleteByReservationId(adventureReservation.getId());
        adventureReservationCancellationRepository.save(adventureReservationCancellation);
        penaltyService.addPenalty(adventureReservation.getClient().getUsername());
        return true;
    }

    @Override
    public boolean clientHasCancellationWithInstructorInPeriod(AdventureReservationDto adventureReservationDto) {
        return adventureReservationCancellationRepository.clientHasCancellationWithInstructorInPeriod(fishingInstructorService.findByUsername(adventureReservationDto.getOwnersUsername()).getId(), clientService.findByUsername(adventureReservationDto.getClientUsername()).getId(), adventureReservationDto.getStartDate(), adventureReservationDto.getEndDate());
    }
}
