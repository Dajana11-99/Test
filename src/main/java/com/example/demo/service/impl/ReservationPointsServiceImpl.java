package com.example.demo.service.impl;

import com.example.demo.model.ReservationPoints;
import com.example.demo.repository.ReservationPointsRepository;
import com.example.demo.service.ReservationPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
