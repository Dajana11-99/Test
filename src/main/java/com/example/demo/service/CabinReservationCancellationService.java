package com.example.demo.service;


import com.example.demo.dto.CabinReservationDto;

public interface CabinReservationCancellationService {

    boolean addCancellation(CabinReservationDto cabinReservationDto);

    boolean clientHasCancellationForCabinInPeriod(CabinReservationDto cabinReservation);

}
