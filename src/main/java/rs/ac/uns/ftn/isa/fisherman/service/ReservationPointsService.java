package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.ReservationPoints;

public interface ReservationPointsService {
    void update(ReservationPoints reservationPoints);

    ReservationPoints get();
}
