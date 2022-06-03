package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.InstructorQuickReport;
import rs.ac.uns.ftn.isa.fisherman.repository.InstructorQuickReportRepository;
import rs.ac.uns.ftn.isa.fisherman.service.InstructorQuickReportService;

@Service
public class InstructorQuickReportImpl implements InstructorQuickReportService {
    @Autowired
    private InstructorQuickReportRepository instructorQuickReportRepository;

    @Override
    public void save(InstructorQuickReport reservationReport) {
        instructorQuickReportRepository.save(reservationReport);
    }
}
