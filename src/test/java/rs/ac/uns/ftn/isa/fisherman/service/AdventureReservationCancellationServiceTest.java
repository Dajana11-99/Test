package rs.ac.uns.ftn.isa.fisherman.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.isa.fisherman.dto.AdventureReservationDto;
import rs.ac.uns.ftn.isa.fisherman.model.AdventureReservation;
import rs.ac.uns.ftn.isa.fisherman.model.AdventureReservationCancellation;
import rs.ac.uns.ftn.isa.fisherman.model.Client;
import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;
import rs.ac.uns.ftn.isa.fisherman.repository.AdventureReservationCancellationRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.AdventureReservationRepository;
import rs.ac.uns.ftn.isa.fisherman.service.impl.AdventureReservationCancellationImpl;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdventureReservationCancellationServiceTest {
    @Mock
    private AdventureReservationCancellationRepository adventureReservationCancellationRepository;
    @Mock
    private FishingInstructorService fishingInstructorService;
    @Mock
    private ClientService clientService;
    @Mock
    private AdventureReservationRepository adventureReservationRepository;
    @InjectMocks
    private AdventureReservationCancellationImpl adventureReservationCancellationService;

    @Test
    public void testClientHasCancellationWithInstructorInPeriod(){
        Client client1 = new Client(1L, "testUser");
        Client client2 = new Client(2L, "testUser2nd");
        Client client3 = new Client(3L, "testUser3rd");
        FishingInstructor fishingInstructor1 = new FishingInstructor(1L, "testInstructor1");
        FishingInstructor fishingInstructor2 = new FishingInstructor(2L, "testInstructor2");

        when(adventureReservationCancellationRepository.findAll()).thenReturn(Arrays.asList(
                new AdventureReservationCancellation(1L, client1, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(5), fishingInstructor1),
                new AdventureReservationCancellation(2L, client2, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3), fishingInstructor1),
                new AdventureReservationCancellation(3L, client2, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(5), fishingInstructor2),
                new AdventureReservationCancellation(4L, client3, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(5), fishingInstructor1),
                new AdventureReservationCancellation(5L, client3, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(5), fishingInstructor2)));
        when(clientService.findByUsername("testUser")).thenReturn(client1);
        when(clientService.findByUsername("testUser2nd")).thenReturn(client2);
        when(clientService.findByUsername("testUser3rd")).thenReturn(client3);
        when(fishingInstructorService.findByUsername("testInstructor1")).thenReturn(fishingInstructor1);
        when(fishingInstructorService.findByUsername("testInstructor2")).thenReturn(fishingInstructor2);

        assertThat(adventureReservationCancellationService.clientHasCancellationWithInstructorInPeriod(fishingInstructor1.getUsername(), client1.getUsername(), LocalDateTime.now().plusDays(4), LocalDateTime.now().plusDays(6))).isTrue();
        assertThat(adventureReservationCancellationService.clientHasCancellationWithInstructorInPeriod(fishingInstructor1.getUsername(), client2.getUsername(), LocalDateTime.now().plusDays(4), LocalDateTime.now().plusDays(5))).isFalse();
        assertThat(adventureReservationCancellationService.clientHasCancellationWithInstructorInPeriod(fishingInstructor2.getUsername(), client1.getUsername(), LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(4))).isFalse();
        assertThat(adventureReservationCancellationService.clientHasCancellationWithInstructorInPeriod(fishingInstructor2.getUsername(), client2.getUsername(), LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(4))).isTrue();
    }
    @Test
    public void testAddCancellation(){
        AdventureReservation adventureReservation = new AdventureReservation();
        adventureReservation.setId(1L);
        adventureReservation.setStartDate(LocalDateTime.now().plusDays(2));
        AdventureReservationDto adventureReservationDto = new AdventureReservationDto();
        adventureReservationDto.setId(1L);

        when(adventureReservationRepository.getById(1L)).thenReturn(adventureReservation);

        assertThat(adventureReservationCancellationService.addCancellation(adventureReservationDto)).isFalse();
    }
}
