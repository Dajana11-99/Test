package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.dto.AdventureReservationDto;
import rs.ac.uns.ftn.isa.fisherman.dto.QuickReservationAdventureDto;

import java.time.LocalDateTime;

public interface AdventureReservationCancellationService {

    boolean addCancellation(AdventureReservationDto adventureReservationDto);

    boolean clientHasCancellationWithInstructorInPeriod(String ownersUsername, String clientUsername, LocalDateTime startDate, LocalDateTime endDate);

    boolean addCancellationQuickReservation(QuickReservationAdventureDto adventureReservationDto);
}
