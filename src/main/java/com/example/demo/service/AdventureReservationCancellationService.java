package com.example.demo.service;

import com.example.demo.dto.AdventureReservationDto;
import com.example.demo.dto.QuickReservationAdventureDto;

import java.time.LocalDateTime;

public interface AdventureReservationCancellationService {

    boolean addCancellation(AdventureReservationDto adventureReservationDto);

    boolean clientHasCancellationWithInstructorInPeriod(String ownersUsername, String clientUsername, LocalDateTime startDate, LocalDateTime endDate);
    boolean addCancellationQuickReservation(QuickReservationAdventureDto adventureReservationDto);

}
