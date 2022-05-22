package com.example.demo.service.impl;

import com.example.demo.model.Penalty;
import com.example.demo.repository.PenaltyRepository;
import com.example.demo.service.ClientService;
import com.example.demo.service.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
