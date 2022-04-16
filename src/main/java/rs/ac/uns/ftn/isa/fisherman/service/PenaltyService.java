package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.Penalty;

import java.util.Set;

public interface PenaltyService {

    void addPenalty(String clientUsername);
    boolean isUserBlockedFromReservation(String username);
    Set<Penalty> getUserPenalties(String username);
}
