package com.example.demo.service;

import com.example.demo.model.AvailableCabinPeriod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface AvailableCabinPeriodService {
    Set<AvailableCabinPeriod> getAvailablePeriod(Long id);

    boolean setAvailableCabinPeriod(AvailableCabinPeriod availableCabinPeriod);

    List<AvailableCabinPeriod> findAll();

    boolean cabinIsAvailable(Long cabinId, LocalDateTime start, LocalDateTime end);

    boolean deleteAvailableCabinsPeriod(AvailableCabinPeriod availablePeriod);

    boolean editAvailableCabinsPeriod(AvailableCabinPeriod oldPeriod, AvailableCabinPeriod newPeriod);
}
