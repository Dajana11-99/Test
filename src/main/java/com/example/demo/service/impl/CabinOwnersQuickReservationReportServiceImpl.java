package com.example.demo.service.impl;

import com.example.demo.model.CabinQuickReservationReport;
import com.example.demo.repository.CabinOwnersQuickReservationReportRepository;
import com.example.demo.service.CabinOwnersQuickReservationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CabinOwnersQuickReservationReportServiceImpl implements CabinOwnersQuickReservationReportService {
    @Autowired
    CabinOwnersQuickReservationReportRepository cabinOwnersQuickReservationReportRepository;
    @Override
    public void save(CabinQuickReservationReport reservationReport) {
        cabinOwnersQuickReservationReportRepository.save(reservationReport);
    }
}
