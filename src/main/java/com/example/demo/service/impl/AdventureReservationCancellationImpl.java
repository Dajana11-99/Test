package com.example.demo.service.impl;

import com.example.demo.dto.AdventureReservationDto;
import com.example.demo.dto.QuickReservationAdventureDto;
import com.example.demo.model.AdventureReservation;
import com.example.demo.model.AdventureReservationCancellation;
import com.example.demo.model.QuickReservationAdventure;
import com.example.demo.repository.AdventureReservationCancellationRepository;
import com.example.demo.repository.AdventureReservationRepository;
import com.example.demo.repository.QuickReservationAdventureRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    @Autowired
    private QuickReservationAdventureRepository quickReservationAdventureRepository;
    @Override
    public boolean addCancellation(AdventureReservationDto adventureReservationDto) {
        AdventureReservation adventureReservation = adventureReservationRepository.getById(adventureReservationDto.getId());
        AdventureReservationCancellation adventureReservationCancellation = new AdventureReservationCancellation(null, adventureReservation.getClient(), adventureReservation.getStartDate(), adventureReservation.getEndDate(), adventureReservation.getFishingInstructor());
        adventureReservation.setAddedAdditionalServices(new HashSet<>());
        System.out.println("STIGAO"+ adventureReservation.getId());
        System.out.println("STIGAO"+ adventureReservation.getClient());
        System.out.println("STIGAO"+ adventureReservation.getFishingInstructor());
        reservationPaymentService.resetLoyaltyStatusAfterCancellation(adventureReservation.getClient(), adventureReservation.getFishingInstructor());
        adventureReservationRepository.save(adventureReservation);
        adventureReservationRepository.deleteByReservationId(adventureReservation.getId());
        adventureReservationCancellationRepository.save(adventureReservationCancellation);
        penaltyService.addPenalty(adventureReservation.getClient().getUsername());
        return true;
    }

    private LocalDateTime getDateWithoutTime(LocalDateTime dateTime) {
        dateTime.minusHours(dateTime.getHour());
        dateTime.minusMinutes(dateTime.getMinute());
        dateTime.minusSeconds(dateTime.getSecond());
        return dateTime;
    }

    @Override
    public boolean clientHasCancellationWithInstructorInPeriod(String ownersUsername, String clientUsername, LocalDateTime startDate, LocalDateTime endDate) {
        Long clientId = clientService.findByUsername(clientUsername).getId();
        Long instructorId = fishingInstructorService.findByUsername(ownersUsername).getId();
        for(AdventureReservationCancellation adventureReservationCancellation: adventureReservationCancellationRepository.findAll())
            if(!adventureReservationCancellation.getStartDate().isAfter(endDate) && !adventureReservationCancellation.getEndDate().isBefore(startDate)
                    && adventureReservationCancellation.getClient().getId().equals(clientId) && adventureReservationCancellation.getFishingInstructor().getId().equals(instructorId))
                return true;
        return false;
    }

    @Override
    public boolean addCancellationQuickReservation(QuickReservationAdventureDto adventureReservationDto) {
        QuickReservationAdventure quickReservationAdventure = quickReservationAdventureRepository.getById(adventureReservationDto.getId());
        AdventureReservationCancellation adventureReservationCancellation = new AdventureReservationCancellation(null, quickReservationAdventure.getClient(), quickReservationAdventure.getStartDate(), quickReservationAdventure.getEndDate(), quickReservationAdventure.getFishingInstructor());
        reservationPaymentService.resetLoyaltyStatusAfterCancellation(quickReservationAdventure.getClient(), quickReservationAdventure.getFishingInstructor());
        penaltyService.addPenalty(quickReservationAdventure.getClient().getUsername());
        quickReservationAdventure.setClient(null);
        quickReservationAdventureRepository.save(quickReservationAdventure);
        adventureReservationCancellationRepository.save(adventureReservationCancellation);
        return true;
    }
}
