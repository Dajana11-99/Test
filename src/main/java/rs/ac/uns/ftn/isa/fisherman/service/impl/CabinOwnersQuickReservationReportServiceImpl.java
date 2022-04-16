package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.CabinQuickReservationReport;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinOwnersQuickReservationReportRepository;
import rs.ac.uns.ftn.isa.fisherman.service.CabinOwnersQuickReservationReportService;
@Service
public class CabinOwnersQuickReservationReportServiceImpl implements CabinOwnersQuickReservationReportService {
    @Autowired
    CabinOwnersQuickReservationReportRepository cabinOwnersQuickReservationReportRepository;
    @Override
    public void save(CabinQuickReservationReport reservationReport) {
        cabinOwnersQuickReservationReportRepository.save(reservationReport);
    }
}
