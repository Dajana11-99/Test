package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.dto.CabinReservationDto;

public interface CabinReservationCancellationService {

    boolean addCancellation(CabinReservationDto cabinReservationDto);

    boolean clientHasCancellationForCabinInPeriod(CabinReservationDto cabinReservation);

}
