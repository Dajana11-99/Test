package com.example.demo.service.impl;

import com.example.demo.model.InstructorQuickReport;
import com.example.demo.repository.InstructorQuickReportRepository;
import com.example.demo.service.InstructorQuickReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorQuickReportImpl implements InstructorQuickReportService {
    @Autowired
    private InstructorQuickReportRepository instructorQuickReportRepository;

    @Override
    public void save(InstructorQuickReport reservationReport) {
        instructorQuickReportRepository.save(reservationReport);
    }
}
