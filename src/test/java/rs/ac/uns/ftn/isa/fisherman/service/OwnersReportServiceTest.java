package rs.ac.uns.ftn.isa.fisherman.service;

import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinOwnersReservationReportRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.OwnersReportRepository;
import rs.ac.uns.ftn.isa.fisherman.service.impl.CabinOwnersReservationReportServiceImpl;
import rs.ac.uns.ftn.isa.fisherman.service.impl.OwnersReportServiceImpl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OwnersReportServiceTest {

    @Mock
    private OwnersReportRepository ownersReportRepository;

    @InjectMocks
    private OwnersReportServiceImpl ownersReportService;


    @Test
    public void testFindAllUnApprovedReports() {

        OwnersReport ownersReport = new OwnersReport(1L,true,"BAD","co@gmail.com","cl@gmail.com",false);
        OwnersReport ownersReport1 = new OwnersReport(2L,true,"BAD","co@gmail.com","cl@gmail.com",false);
        OwnersReport ownersReport2 = new OwnersReport(3L,true,"BAD","co@gmail.com","cl@gmail.com",true);
        OwnersReport ownersReport3 = new OwnersReport(4L,false,"BAD","co@gmail.com","cl@gmail.com",false);

        when(ownersReportRepository.findAll()).thenReturn(Arrays.asList(ownersReport1,ownersReport,ownersReport2,ownersReport3));

        List<OwnersReport> ownersReports = ownersReportService.getAllUnApprovedReports();
        List<OwnersReport> allReports = ownersReportService.findAll();
        assertThat(ownersReports).hasSize(allReports.size()-2);

    }

    @Test
    public void testUpdateReviewStatus() {

        OwnersReport ownersReport = new OwnersReport(100L,true,"BAD","co@gmail.com","cl@gmail.com",false);

        when(ownersReportRepository.getById(100L)).thenReturn(ownersReport);

        OwnersReport ownersReportExists = ownersReportService.setReviewStatus(ownersReport.getId());

        when(ownersReportRepository.save(ownersReportExists)).thenReturn(ownersReportExists);

        OwnersReport result = ownersReportService.findById(100L);

        assertThat(result.isApproved()).isEqualTo(true);

    }


}
