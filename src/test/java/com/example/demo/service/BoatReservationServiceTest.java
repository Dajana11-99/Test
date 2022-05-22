package com.example.demo.service;

import com.example.demo.model.Boat;
import com.example.demo.model.BoatReservation;
import com.example.demo.model.Client;
import com.example.demo.model.PaymentInformation;
import com.example.demo.repository.BoatReservationRepository;
import com.example.demo.service.impl.BoatReservationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoatReservationServiceTest {
    @Mock
    private BoatReservationRepository boatReservationRepository;

    @InjectMocks
    private BoatReservationServiceImpl boatReservationService;

    @Test
    public void testSumProfitByHours() {
        BoatReservation firstReservation=new BoatReservation(2L,LocalDateTime.now().plusHours(6),
                LocalDateTime.now().plusHours(7),null,new PaymentInformation(70.00,20.00,50.00),
                false,"bo@gmail.com",null,null,false);
        BoatReservation secondReservation=new BoatReservation(3L,LocalDateTime.now().plusHours(8),
                LocalDateTime.now().plusHours(9),null,new PaymentInformation(70.00,20.00,50.00),
                false,"bo@gmail.com",null,null,false);

        List<BoatReservation> reservations = Arrays.asList(firstReservation,secondReservation);
        double profit= boatReservationService.sumProfitOfPricesCalucatedByHours(reservations,LocalDateTime.now().plusHours(5),LocalDateTime.now().plusHours(10));
        assertEquals(100.00,profit,0.001);
    }
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
