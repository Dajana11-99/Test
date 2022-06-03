package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.BoatQuickReservationReport;
import rs.ac.uns.ftn.isa.fisherman.repository.BoatOwnersQuickReservationReportRepository;
import rs.ac.uns.ftn.isa.fisherman.service.BoatOwnersQuickReservationReportService;
@Service
public class BoatOwnersQuickReservationReportServiceImpl implements BoatOwnersQuickReservationReportService {
    @Autowired
    BoatOwnersQuickReservationReportRepository boatOwnersQuickReservationReportRepository;
    @Override
    public void save(BoatQuickReservationReport reservationReport) {
        boatOwnersQuickReservationReportRepository.save(reservationReport);
    }
}
