package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.NewComplaintDto;
import rs.ac.uns.ftn.isa.fisherman.service.CabinOwnerService;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinComplaintRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinOwnerComplaintRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinReservationRepository;
import rs.ac.uns.ftn.isa.fisherman.service.ClientService;
import rs.ac.uns.ftn.isa.fisherman.service.ComplaintService;

import java.time.LocalDateTime;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private CabinReservationRepository cabinReservationRepository;
    @Autowired
    private CabinRepository cabinRepository;
    @Autowired
    private CabinComplaintRepository cabinComplaintRepository;
    @Autowired
    private CabinOwnerComplaintRepository cabinOwnerComplaintRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CabinOwnerService cabinOwnerService;

    @Override
    public boolean addComplaint(NewComplaintDto newComplaintDto) {
        if(!checkIfComplaintIsPossible(newComplaintDto)){
            return false;
        }
        saveComplaint(newComplaintDto);
        return true;
    }
    private boolean checkIfComplaintIsPossible(NewComplaintDto newComplaintDto){
        if(newComplaintDto.getSubjectRole().equals("cabin")){
            CabinReservation cabinReservation = cabinReservationRepository.getById(newComplaintDto.getReservationId());
            return cabinReservationRepository.clientHasReservationInCabin(cabinReservation.getCabin().getId(), clientService.findByUsername(newComplaintDto.getClientUsername()).getId());
        }else if(newComplaintDto.getSubjectRole().equals("cabin owner")){
            CabinReservation cabinReservation = cabinReservationRepository.getById(newComplaintDto.getReservationId());
            CabinOwner cabinOwner = cabinReservation.getCabin().getCabinOwner();
            return cabinReservationRepository.clientHasReservationInOwnersCabin(cabinRepository.findCabinsIdByOwnersId(cabinOwnerService.findByUsername(cabinOwner.getUsername()).getId()), clientService.findByUsername(newComplaintDto.getClientUsername()).getId());
        }
        return false;
    }
    private void saveComplaint(NewComplaintDto newComplaintDto){
        if(newComplaintDto.getSubjectRole().equals("cabin")){
            CabinReservation cabinReservation = cabinReservationRepository.getById(newComplaintDto.getReservationId());
           cabinComplaintRepository.save(new CabinComplaint(null, newComplaintDto.getText(), LocalDateTime.now(), false, clientService.findByUsername(newComplaintDto.getClientUsername()), cabinReservation.getCabin()));
        }else if(newComplaintDto.getSubjectRole().equals("cabin owner")){
            CabinReservation cabinReservation = cabinReservationRepository.getById(newComplaintDto.getReservationId());
           cabinOwnerComplaintRepository.save(new CabinOwnerComplaint(null, newComplaintDto.getText(), LocalDateTime.now(), false, clientService.findByUsername(newComplaintDto.getClientUsername()), cabinReservation.getCabin().getCabinOwner()));
        }
    }
}
