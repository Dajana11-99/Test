package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.Penalty;
import rs.ac.uns.ftn.isa.fisherman.repository.PenaltyRepository;
import rs.ac.uns.ftn.isa.fisherman.service.ClientService;
import rs.ac.uns.ftn.isa.fisherman.service.PenaltyService;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class PenaltyServiceImpl implements PenaltyService {

    @Autowired
    private PenaltyRepository penaltyRepository;
    @Autowired
    private ClientService clientService;

    public void addPenalty(String clientUsername){
        penaltyRepository.save(new Penalty(null, LocalDateTime.now(), clientService.findByUsername(clientUsername)));
    }

    public boolean isUserBlockedFromReservation(String username){
        return penaltyRepository.isUserBlockedFromReservation(clientService.findByUsername(username).getId());
    }

    public Set<Penalty> getUserPenalties(String username){
        return penaltyRepository.getUserPenalties(clientService.findByUsername(username).getId());
    }
}
