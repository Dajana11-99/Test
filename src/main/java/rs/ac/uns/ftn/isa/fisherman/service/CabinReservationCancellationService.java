package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.dto.CabinReservationDto;
import rs.ac.uns.ftn.isa.fisherman.dto.QuickReservationCabinDto;

import java.time.LocalDateTime;

public interface CabinReservationCancellationService {

    boolean addCancellation(CabinReservationDto cabinReservationDto);

    boolean clientHasCancellationForCabinInPeriod(Long cabinId, String clientUsername, LocalDateTime startDate, LocalDateTime endDate);

    boolean addCancellationQuickReservation(QuickReservationCabinDto cabinReservationDto);
}
