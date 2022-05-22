package com.example.demo.service.impl;

import com.example.demo.dto.CabinReservationDto;
import com.example.demo.model.CabinReservation;
import com.example.demo.model.CabinReservationCancellation;
import com.example.demo.repository.CabinReservationCancellationRepository;
import com.example.demo.repository.CabinReservationRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class CabinReservationCancellationServiceImpl implements CabinReservationCancellationService {

    @Autowired
    private CabinReservationCancellationRepository cabinReservationCancellationRepository;
    @Autowired
    private CabinReservationRepository cabinReservationRepository;
    @Autowired
    private CabinOwnerService cabinOwnerService;
    @Autowired
    private ReservationPaymentService reservationPaymentService;
    @Autowired
    private PenaltyService penaltyService;
    @Autowired
    private ClientService clientService;

    @Override
    public boolean addCancellation(CabinReservationDto cabinReservationDto) {
        CabinReservation cabinReservation = cabinReservationRepository.getById(cabinReservationDto.getId());
        CabinReservationCancellation cabinReservationCancellation = new CabinReservationCancellation(null, cabinReservation.getClient(), cabinReservation.getStartDate(), cabinReservation.getEndDate(), cabinReservation.getCabin());
        cabinReservation.setAddedAdditionalServices(new HashSet<>());
        reservationPaymentService.resetLoyaltyStatusAfterCancellation(cabinReservation.getClient(), cabinOwnerService.findByUsername(cabinReservationDto.getCabinDto().getOwnerUsername()));
        cabinReservationRepository.save(cabinReservation);
        cabinReservationRepository.deleteByReservationId(cabinReservation.getId());
        cabinReservationCancellationRepository.save(cabinReservationCancellation);
        penaltyService.addPenalty(cabinReservation.getClient().getUsername());
        return true;
    }

    @Override
    public boolean clientHasCancellationForCabinInPeriod(CabinReservationDto cabinReservation) {
        return cabinReservationCancellationRepository.clientHasCancellationForCabinInPeriod(cabinReservation.getCabinDto().getId(), clientService.findByUsername(cabinReservation.getClientUsername()).getId(), cabinReservation.getStartDate(), cabinReservation.getEndDate());
    }
}
