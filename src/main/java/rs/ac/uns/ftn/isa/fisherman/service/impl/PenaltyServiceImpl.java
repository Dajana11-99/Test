package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.Penalty;
import rs.ac.uns.ftn.isa.fisherman.repository.PenaltyRepository;
import rs.ac.uns.ftn.isa.fisherman.service.ClientService;
import rs.ac.uns.ftn.isa.fisherman.service.PenaltyService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PenaltyServiceImpl implements PenaltyService {

    @Autowired
    private PenaltyRepository penaltyRepository;
    @Autowired
    private ClientService clientService;

    public void addPenalty(String clientUsername){
        deleteOldPenalties();
        penaltyRepository.save(new Penalty(null, LocalDateTime.now(), clientService.findByUsername(clientUsername)));
    }

    public boolean isUserBlockedFromReservation(String username){
        deleteOldPenalties();
        Long clientId = clientService.findByUsername(username).getId();
        int numberOfPenalties = 0;
        for(Penalty penalty:penaltyRepository.findAll()) {
            if (penalty.getClient().getId().equals(clientId))
                numberOfPenalties++;
            if(numberOfPenalties==3)
                return true;
        }
        return false;
    }

    public List<Penalty> getUserPenalties(String username){
        deleteOldPenalties();
        Long clientId = clientService.findByUsername(username).getId();
        List<Penalty> userPenalties = new ArrayList<>();
        for(Penalty penalty:penaltyRepository.findAll()) {
            if (penalty.getClient().getId().equals(clientId))
                userPenalties.add(penalty);
        }
        return userPenalties;
    }

    @Override
    public void deleteOldPenalties() {
        penaltyRepository.deleteOldPenalties(LocalDateTime.now().withDayOfMonth(1));
    }
}
