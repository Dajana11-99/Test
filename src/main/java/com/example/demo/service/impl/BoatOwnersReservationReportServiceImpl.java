package com.example.demo.service.impl;

import com.example.demo.model.BoatOwnersReservationReport;
import com.example.demo.repository.BoatOwnersReservationReportRepository;
import com.example.demo.service.BoatOwnersReservationReportService;
import com.google.auto.value.AutoAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BoatOwnersReservationReportServiceImpl implements BoatOwnersReservationReportService {
    @Autowired
    private BoatOwnersReservationReportRepository boatOwnersReservationReportRepository;
    @Override
    public void save(BoatOwnersReservationReport reservationReport) {
        boatOwnersReservationReportRepository.save(reservationReport);
    }
}
