package com.example.demo.service;

import com.example.demo.dto.AdventureReservationDto;
public interface AdventureReservationCancellationService {

    boolean addCancellation(AdventureReservationDto adventureReservationDto);

    boolean clientHasCancellationWithInstructorInPeriod(AdventureReservationDto adventureReservationDto);

}
