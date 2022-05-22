package com.example.demo.service.impl;

import com.example.demo.model.CabinOwnersReservationReport;
import com.example.demo.repository.CabinOwnersReservationReportRepository;
import com.example.demo.service.CabinOwnersReservationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CabinOwnersReservationReportServiceImpl implements CabinOwnersReservationReportService {
    @Autowired
    private CabinOwnersReservationReportRepository cabinOwnersReservationReportRepository;
    @Override
    public void save(CabinOwnersReservationReport reservationReport) {
        cabinOwnersReservationReportRepository.save(reservationReport);
    }
}
