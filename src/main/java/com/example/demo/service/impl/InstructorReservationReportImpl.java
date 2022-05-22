package com.example.demo.service.impl;
import com.example.demo.model.InstructorReservationReport;
import com.example.demo.repository.InstructorReservationReportRepository;
import com.example.demo.service.InstructorReservationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorReservationReportImpl implements InstructorReservationReportService {
    @Autowired
    private InstructorReservationReportRepository instructorReservationReportRepository;

    @Override
   public void save(InstructorReservationReport reservationReport){
        instructorReservationReportRepository.save(reservationReport);

    }
}
