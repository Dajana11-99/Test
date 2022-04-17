package rs.ac.uns.ftn.isa.fisherman.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.repository.BoatReservationRepository;
import rs.ac.uns.ftn.isa.fisherman.service.impl.BoatReservationServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoatReservationServiceTest {
    @Mock
    private BoatReservationRepository boatReservationRepository;

    @InjectMocks
    private BoatReservationServiceImpl boatReservationService;

  /*  @Test
    public void testSumProfitByHours() {

        BoatReservation firstReservation = spy(BoatReservation.class);
        firstReservation.setPaymentInformation(new PaymentInformation(70.00,20.00,50.00));
        firstReservation.setStartDate(LocalDateTime.now().plusHours(6));
        firstReservation.setEndDate(LocalDateTime.now().plusHours(7));
        firstReservation.setOwnersUsername("bo@gmail.com");

        BoatReservation secondReservation = spy(BoatReservation.class);
        secondReservation.setPaymentInformation(new PaymentInformation(70.00,20.00,50.00));
        secondReservation.setStartDate(LocalDateTime.now().plusHours(8));
        secondReservation.setEndDate(LocalDateTime.now().plusHours(9));
        secondReservation.setOwnersUsername("bo@gmail.com");

        when(boatReservationRepository.findAll()).thenReturn(Arrays.asList(secondReservation,firstReservation));
        when(boatReservationRepository.findReservationsInPeriodToSumProfit("bo@gmail.com",
                LocalDateTime.now().plusHours(5),LocalDateTime.now().plusHours(10))).thenReturn(Arrays.asList(secondReservation,firstReservation));
        List<BoatReservation> reservations= new ArrayList<>();
         reservations = boatReservationService.findReservationsToSumProfit("bo@gmail.com",
                LocalDateTime.now().plusHours(5),LocalDateTime.now().plusHours(10));
        System.out.println("asasasa"+reservations.size());
        double profit= boatReservationService.sumProfitOfPricesCalucatedByHours(reservations,LocalDateTime.now().plusHours(5),LocalDateTime.now().plusHours(10));
        //assertThat(reservations).isNotEmpty();
        assertEquals(100.00,profit,0.001);
    }*/
    @Test
    public void testFindPastReservationsForBoatOwnerSuccessfull() {
        BoatReservation firstReservation=new BoatReservation(1L,LocalDateTime.now().minusDays(7),LocalDateTime.now().minusDays(6),new Client(),new PaymentInformation(),false,"bo@gmail.com",new Boat(),null,false);
        BoatReservation secondReservation=new BoatReservation(2L,LocalDateTime.now().minusDays(2),LocalDateTime.now().minusDays(1),new Client(),new PaymentInformation(),false,"bo@gmail.com",new Boat(),null,false);
        BoatReservation thirdReservation=new BoatReservation(3L,LocalDateTime.now().plusDays(2),LocalDateTime.now().plusDays(3),new Client(),new PaymentInformation(),false,"bo@gmail.com",new Boat(),null,false);

        when(boatReservationRepository.findAll()).thenReturn(Arrays.asList(firstReservation,secondReservation,thirdReservation));
        when(boatReservationRepository.getReservationsByOwnerUsername("bo@gmail.com")).thenReturn(Arrays.asList(firstReservation,secondReservation,thirdReservation));

        List<BoatReservation> pastReservations= boatReservationService.getPastReservations("bo@gmail.com");
        assertThat(pastReservations).hasSize(2);
        assertThat(pastReservations).isNotNull();
    }

    @Test
    public void testFindPastReservationsForBoatOwnerUnsuccessfull() {
        BoatReservation firstReservation=new BoatReservation(1L,LocalDateTime.now().plusDays(6),LocalDateTime.now().plusDays(7),new Client(),new PaymentInformation(),false,"bo@gmail.com",new Boat(),null,false);
        BoatReservation secondReservation=new BoatReservation(2L,LocalDateTime.now().plusDays(4),LocalDateTime.now().plusDays(5),new Client(),new PaymentInformation(),false,"bo@gmail.com",new Boat(),null,false);
        BoatReservation thirdReservation=new BoatReservation(3L,LocalDateTime.now().plusDays(2),LocalDateTime.now().plusDays(3),new Client(),new PaymentInformation(),false,"bo@gmail.com",new Boat(),null,false);

        when(boatReservationRepository.findAll()).thenReturn(Arrays.asList(firstReservation,secondReservation,thirdReservation));
        when(boatReservationRepository.getReservationsByOwnerUsername("bo@gmail.com")).thenReturn(Arrays.asList(firstReservation,secondReservation,thirdReservation));

        List<BoatReservation> pastReservations= boatReservationService.getPastReservations("bo@gmail.com");
        assertThat(pastReservations).isEmpty();
    }

}
