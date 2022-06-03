package rs.ac.uns.ftn.isa.fisherman.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.isa.fisherman.model.Client;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationBoat;
import rs.ac.uns.ftn.isa.fisherman.repository.QuickReservationBoatRepository;
import rs.ac.uns.ftn.isa.fisherman.service.impl.QuickReservationBoatServiceImpl;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuickReservationBoatServiceTest {
    @Mock
    private QuickReservationBoatRepository quickReservationBoatRepository;
    @Mock
    private ClientService clientService;
    @InjectMocks
    private QuickReservationBoatServiceImpl quickReservationBoatService;

    @Test
    public void testGetAvailableReservations(){
        when(quickReservationBoatRepository.findAll()).thenReturn(Arrays.asList(
                new QuickReservationBoat(1L, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), null),
                new QuickReservationBoat(2L, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), new Client(1L, "test")),
                new QuickReservationBoat(3L, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(2), null),
                new QuickReservationBoat(4L, LocalDateTime.now().plusDays(4), LocalDateTime.now().plusDays(5), null)
        ));

        assertThat(quickReservationBoatService.getAvailableReservations()).hasSize(2);
    }
    @Test
    public void testGetClientQuickReservationsHistory(){
        Client client1 = new Client(1L, "testUser");
        Client client2 = new Client(2L, "testUser2nd");
        Client client3 = new Client(3L, "testUser3rd");

        when(clientService.findByUsername("testUser")).thenReturn(client1);
        when(clientService.findByUsername("testUser2nd")).thenReturn(client2);
        when(clientService.findByUsername("testUser3rd")).thenReturn(client3);
        when(quickReservationBoatRepository.findAll()).thenReturn(Arrays.asList(
                new QuickReservationBoat(1L, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(2), client1),
                new QuickReservationBoat(2L, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(2), client1),
                new QuickReservationBoat(3L, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(2), client2),
                new QuickReservationBoat(4L, LocalDateTime.now().plusDays(4), LocalDateTime.now().plusDays(5), client1),
                new QuickReservationBoat(5L, LocalDateTime.now().minusDays(4), LocalDateTime.now().plusDays(5), null)
        ));

        assertThat(quickReservationBoatService.getClientQuickReservationsHistory("testUser")).hasSize(2);
        assertThat(quickReservationBoatService.getClientQuickReservationsHistory("testUser2nd")).hasSize(1);
        assertThat(quickReservationBoatService.getClientQuickReservationsHistory("testUser3rd")).isEmpty();
    }
}
