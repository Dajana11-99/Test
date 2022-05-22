package com.example.demo.service;


import com.example.demo.model.ReservationPoints;

public interface ReservationPointsService {
    void update(ReservationPoints reservationPoints);

    ReservationPoints get();
}
