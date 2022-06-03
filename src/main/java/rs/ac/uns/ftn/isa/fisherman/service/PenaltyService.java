package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.Penalty;

import java.util.List;

public interface PenaltyService {

    void addPenalty(String clientUsername);
    boolean isUserBlockedFromReservation(String username);
    List<Penalty> getUserPenalties(String username);
    void deleteOldPenalties();
}
