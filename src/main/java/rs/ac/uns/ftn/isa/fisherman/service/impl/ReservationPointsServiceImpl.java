package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.ReservationPoints;
import rs.ac.uns.ftn.isa.fisherman.repository.ReservationPointsRepository;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationPointsService;
@Service
public class ReservationPointsServiceImpl implements ReservationPointsService {
    @Autowired
    private ReservationPointsRepository reservationPointsRepository;
    @Override
    public void update(ReservationPoints reservationPoints) {
        reservationPointsRepository.save(reservationPoints);

    }

    @Override
    public ReservationPoints get() {
       return reservationPointsRepository.findAll().get(0);
    }

}
