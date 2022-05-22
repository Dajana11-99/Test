package com.example.demo.service;



import com.example.demo.model.Penalty;

import java.util.Set;

public interface PenaltyService {

    void addPenalty(String clientUsername);
    boolean isUserBlockedFromReservation(String username);
    Set<Penalty> getUserPenalties(String username);
}
