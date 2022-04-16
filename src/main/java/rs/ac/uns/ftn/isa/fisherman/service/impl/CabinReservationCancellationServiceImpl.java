package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinReservationDto;
import rs.ac.uns.ftn.isa.fisherman.service.*;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservationCancellation;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinReservationCancellationRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinReservationRepository;

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
        CabinOwner cabinOwner = cabinOwnerService.findByUsername(cabinReservationDto.getCabinDto().getOwnerUsername());
        if(cabinReservationDto.getStartDate().minusDays(3).isBefore(LocalDateTime.now())||
                (cabinReservationCancellationRepository.findById(cabinReservationDto.getId()).isPresent()))
            return false;

        CabinReservation cabinReservation = cabinReservationRepository.getById(cabinReservationDto.getId());
        CabinReservationCancellation cabinReservationCancellation = new CabinReservationCancellation(null, cabinReservation.getClient(), cabinReservation.getStartDate(), cabinReservation.getEndDate(), cabinReservation.getCabin());
        cabinReservation.setAddedAdditionalServices(new HashSet<>());
        reservationPaymentService.resetLoyaltyStatusAfterCancellation(cabinReservation.getClient(),cabinOwner);
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
