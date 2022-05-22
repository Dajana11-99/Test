package com.example.demo.repository;

import com.example.demo.model.ReservationPoints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationPointsRepository extends JpaRepository<ReservationPoints,Long> {
}
