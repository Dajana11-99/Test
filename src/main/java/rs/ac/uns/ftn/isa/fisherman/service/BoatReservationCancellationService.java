package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.dto.BoatReservationDto;
import rs.ac.uns.ftn.isa.fisherman.dto.QuickReservationBoatDto;

import java.time.LocalDateTime;

public interface BoatReservationCancellationService {

    boolean addCancellation(BoatReservationDto boatReservationDto);

    boolean clientHasCancellationForBoatInPeriod(Long boatId, String clientUsername, LocalDateTime startDate, LocalDateTime endDate);

    boolean addCancellationQuickReservation(QuickReservationBoatDto quickReservationBoatDto);
}
