package rs.ac.uns.ftn.isa.fisherman.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.repository.PenaltyRepository;
import rs.ac.uns.ftn.isa.fisherman.service.impl.PenaltyServiceImpl;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PenaltyServiceTest {

    @Mock
    private ClientService clientService;
    @Mock
    private PenaltyRepository penaltyRepository;
    @InjectMocks
    private PenaltyServiceImpl penaltyService;

    @Test
    public void testGetUserPenalties() {
        Client client1 = new Client(1L, "testUser");
        Client client2 = new Client(2L, "testUser2nd");

        when(penaltyRepository.findAll()).thenReturn(Arrays.asList(new Penalty(1L, LocalDateTime.now(), client1), new Penalty(2L, LocalDateTime.now(), client1),new Penalty(3L, LocalDateTime.now(), client2)));
        when(clientService.findByUsername("testUser")).thenReturn(client1);
        when(clientService.findByUsername("testUser2nd")).thenReturn(client2);

        assertThat(penaltyService.getUserPenalties("testUser")).hasSize(2);
        assertThat(penaltyService.getUserPenalties("testUser2nd")).hasSize(1);
    }

    @Test
    public void testIsUserBlocked(){
        Client client1 = new Client(1L, "testUser");
        Client client2 = new Client(2L, "testUser2nd");

        when(penaltyRepository.findAll()).thenReturn(Arrays.asList(new Penalty(1L, LocalDateTime.now(), client1), new Penalty(2L, LocalDateTime.now(), client1),new Penalty(3L, LocalDateTime.now(), client2),new Penalty(4L, LocalDateTime.now(), client2), new Penalty(5L, LocalDateTime.now(), client1)));
        when(clientService.findByUsername("testUser")).thenReturn(client1);
        when(clientService.findByUsername("testUser2nd")).thenReturn(client2);

        assertThat(penaltyService.isUserBlockedFromReservation("testUser")).isTrue();
        assertThat(penaltyService.isUserBlockedFromReservation("testUser2nd")).isFalse();
    }
}
