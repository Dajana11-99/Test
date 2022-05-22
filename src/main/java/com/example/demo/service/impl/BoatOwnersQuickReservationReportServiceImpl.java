package com.example.demo.service.impl;

import com.example.demo.model.BoatQuickReservationReport;
import com.example.demo.repository.BoatOwnersQuickReservationReportRepository;
import com.example.demo.service.BoatOwnersQuickReservationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BoatOwnersQuickReservationReportServiceImpl implements BoatOwnersQuickReservationReportService {
    @Autowired
    BoatOwnersQuickReservationReportRepository boatOwnersQuickReservationReportRepository;
    @Override
    public void save(BoatQuickReservationReport reservationReport) {
        boatOwnersQuickReservationReportRepository.save(reservationReport);
    }
}
