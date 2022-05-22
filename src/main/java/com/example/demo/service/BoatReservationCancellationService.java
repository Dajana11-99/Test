package com.example.demo.service;


import com.example.demo.dto.BoatReservationDto;

public interface BoatReservationCancellationService {

    boolean addCancellation(BoatReservationDto boatReservationDto);

    boolean clientHasCancellationForBoatInPeriod(BoatReservationDto boatReservationDto);

}
