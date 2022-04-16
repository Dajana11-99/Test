package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.AddNewEvaluationDto;
import rs.ac.uns.ftn.isa.fisherman.model.CabinEvaluation;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwnerEvaluation;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinEvaluationRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinOwnerEvaluationRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinReservationRepository;
import rs.ac.uns.ftn.isa.fisherman.service.CabinOwnerEvaluationService;
import rs.ac.uns.ftn.isa.fisherman.service.CabinService;
import rs.ac.uns.ftn.isa.fisherman.service.ClientService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationCabinService;

import java.time.LocalDateTime;

@Service
public class CabinOwnerEvaluationServiceImpl implements CabinOwnerEvaluationService {

    @Autowired
    ClientService clientService;
    @Autowired
    CabinReservationRepository cabinReservationRepository;
    @Autowired
    CabinOwnerEvaluationRepository cabinOwnerEvaluationRepository;
    @Autowired
    CabinService cabinService;
    @Autowired
    ReservationCabinService cabinReservationService;

    @Override
    public void addEvaluation(AddNewEvaluationDto addNewEvaluationDto) {
        CabinReservation cabinReservation = cabinReservationService.getById(addNewEvaluationDto.getReservationId());
        cabinOwnerEvaluationRepository.save(new CabinOwnerEvaluation(null, LocalDateTime.now(), addNewEvaluationDto.getCommentForTheEntityOwner(), addNewEvaluationDto.getGradeForTheEntityOwner(), false, clientService.findByUsername(addNewEvaluationDto.getClientUsername()), cabinReservation.getCabin().getCabinOwner().getUsername(), cabinReservation.getCabin().getCabinOwner()));
    }
}
