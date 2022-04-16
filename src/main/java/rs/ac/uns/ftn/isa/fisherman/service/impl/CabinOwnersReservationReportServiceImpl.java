package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwnersReservationReport;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinOwnersReservationReportRepository;
import rs.ac.uns.ftn.isa.fisherman.service.CabinOwnersReservationReportService;
@Service
public class CabinOwnersReservationReportServiceImpl implements CabinOwnersReservationReportService {
    @Autowired
    private CabinOwnersReservationReportRepository cabinOwnersReservationReportRepository;
    @Override
    public void save(CabinOwnersReservationReport reservationReport) {
        cabinOwnersReservationReportRepository.save(reservationReport);
    }
}
