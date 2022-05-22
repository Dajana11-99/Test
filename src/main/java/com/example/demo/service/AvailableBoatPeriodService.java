package com.example.demo.service;


import com.example.demo.model.AvailableBoatPeriod;

import java.time.LocalDateTime;
import java.util.Set;

public interface AvailableBoatPeriodService {

    Set<AvailableBoatPeriod> getAvailablePeriod(Long id);

    boolean setAvailableBoatPeriod(AvailableBoatPeriod availableBoatPeriods);

    boolean boatIsAvailable(Long boatId, LocalDateTime start, LocalDateTime end);

    boolean editAvailableBoatsPeriod(AvailableBoatPeriod oldAvailablePeriod, AvailableBoatPeriod newAvailablePeriod);
    boolean deleteAvailableBoatsPeriod(AvailableBoatPeriod availablePeriod);

    Set<AvailableBoatPeriod> findPeriodsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
}
