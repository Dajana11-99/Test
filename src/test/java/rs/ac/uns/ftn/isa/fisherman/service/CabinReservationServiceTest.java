package rs.ac.uns.ftn.isa.fisherman.service;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinReservationRepository;
import rs.ac.uns.ftn.isa.fisherman.service.impl.ReservationCabinServiceImpl;
import java.time.LocalDateTime;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CabinReservationServiceTest {
    @Spy
    private CabinReservationRepository cabinReservationRepository;

    @InjectMocks
    private ReservationCabinServiceImpl reservationCabinService;


   /* @Test
    public void testSumOwnersProfitByDays() {

        CabinReservation firstReservation = spy(CabinReservation.class);
        firstReservation.setPaymentInformation(new PaymentInformation(120.00,20.00,100.00));
        firstReservation.setStartDate(LocalDateTime.now().plusDays(1));
        firstReservation.setEndDate(LocalDateTime.now().plusDays(2));
        firstReservation.setOwnersUsername("co@gmail.com");

        CabinReservation secondReservation = spy(CabinReservation.class);
        secondReservation.setPaymentInformation(new PaymentInformation(70.00,20.00,50.00));
        secondReservation.setStartDate(LocalDateTime.now().plusDays(3));
        secondReservation.setEndDate(LocalDateTime.now().plusDays(4));
        secondReservation.setOwnersUsername("co@gmail.com");


        when(cabinReservationRepository.findAll()).thenReturn(Arrays.asList(firstReservation,secondReservation));
        when(cabinReservationRepository.findReservationsInPeriodToSumProfit("co@gmail.com",
                LocalDateTime.now(),LocalDateTime.now().plusDays(6))).thenReturn(Arrays.asList(firstReservation,secondReservation));
        List<CabinReservation> reservations= new ArrayList<>();
         reservations = reservationCabinService.findReservationsToSumProfit("co@gmail.com",
                LocalDateTime.now(),LocalDateTime.now().plusDays(6));
        double profit= reservationCabinService.sumProfitOfPricesCalculatedByDays(reservations,LocalDateTime.now(),LocalDateTime.now().plusDays(6));

        assertEquals(150.00,profit,0.001);
    }*/
    @Test
    public void testCalculateNumberOfOverlapingDatesForReservationReport() {
        LocalDateTime currentDate=LocalDateTime.now();
        LocalDateTime startReport=currentDate.now().plusDays(1);
        LocalDateTime endReport=currentDate.now().plusDays(10);
        LocalDateTime reservationStart=currentDate.now().minusDays(4);
        LocalDateTime reservationEnd=currentDate.now().plusDays(4);
        long numOfOverlapingDates= reservationCabinService.calculateOverlapingDates(
                startReport,endReport,reservationStart,reservationEnd);
        assertEquals(3,numOfOverlapingDates);
    }

}
