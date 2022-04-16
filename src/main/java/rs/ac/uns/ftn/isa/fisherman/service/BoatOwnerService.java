package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.BoatOwner;

import java.util.List;

public interface BoatOwnerService {
    BoatOwner findByUsername(String ownersUsername);
    void save(BoatOwner boatOwner);
}
