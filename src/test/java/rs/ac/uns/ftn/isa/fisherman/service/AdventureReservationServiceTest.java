package rs.ac.uns.ftn.isa.fisherman.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.isa.fisherman.dto.SearchAvailablePeriodsBoatAndAdventureDto;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.repository.AdventureReservationCancellationRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.AdventureReservationRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.ClientRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.FishingInstructorRepository;
import rs.ac.uns.ftn.isa.fisherman.service.impl.AdventureReservationServiceImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdventureReservationServiceTest {
    @Mock
    private AdventureReservationRepository adventureReservationRepository;
    @Mock
    private AvailableInstructorPeriodService availableInstructorPeriodService;
    @Mock
    private AdventureReservationCancellationRepository adventureReservationCancellationRepository;
    @Mock
    private QuickReservationAdventureService quickReservationAdventureService;
    @Mock
    private ClientService clientService;
    @Mock
    private FishingInstructorRepository fishingInstructorRepository;
    @Mock
    private AdventureService adventureService;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private AdventureReservationCancellationService adventureReservationCancellationService;
    @InjectMocks
    private AdventureReservationServiceImpl adventureReservationService;

    @Test
    public void testGetAvailableAdventures(){
        Address address1 = new Address(53,43,"Serbia","Novi Sad","Dunavska");
        Address address2 = new Address(53,43,"Serbia","Belgrade","Kralja Milana");
        FishingInstructor fishingInstructor1 = new FishingInstructor(1L,"Miroslav","Mirkovic","mirko@gmail.com","123","061435234",address1,new HashSet<>(),new HashSet<>(),"bla bla");
        FishingInstructor fishingInstructor2 = new FishingInstructor(2L,"Slavko","Slavkovic","slavko@gmail.com","123","061435234",address2,new HashSet<>(),new HashSet<>(),"bla bla");
        fishingInstructor1.setRating(4.5);
        fishingInstructor2.setRating(4.0);
        Adventure adventure1 = new Adventure(1L,"Fishing adventure1",address1,"The best.","Licenced instructor.",3,40,"No kids.","Hooks and rails","FREE");
        Adventure adventure2 = new Adventure(2L,"Fishing adventure2",address2,"The best.","Licenced instructor.",5,60,"No kids.","Hooks and rails","FREE");
        Adventure adventure3 = new Adventure(3L,"Fishing adventure3",address1,"The best.","Licenced instructor.",7,80,"No kids.","Hooks and rails","FREE");
        Adventure adventure4 = new Adventure(4L,"Fishing adventure4",address2,"The best.","Licenced instructor.",9,100,"No kids.","Hooks and rails","FREE");
        adventure1.setFishingInstructor(fishingInstructor1);
        adventure2.setFishingInstructor(fishingInstructor1);
        adventure3.setFishingInstructor(fishingInstructor2);
        adventure4.setFishingInstructor(fishingInstructor2);

        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(2);

        when(availableInstructorPeriodService.getAvailableFishingInstructorsIdsForPeriod(startDate, endDate)).thenReturn(Arrays.asList(1L, 2L));
        when(fishingInstructorRepository.findByID(1L)).thenReturn(fishingInstructor1);
        when(fishingInstructorRepository.findByID(2L)).thenReturn(fishingInstructor2);

        when(adventureReservationRepository.instructorHasReservationInPeriod("mirko@gmail.com", startDate, endDate)).thenReturn(false);
        when(quickReservationAdventureService.fishingInstructorNotFree("mirko@gmail.com", startDate, endDate)).thenReturn(false);
        when(adventureReservationRepository.instructorHasReservationInPeriod("slavko@gmail.com", startDate, endDate)).thenReturn(false);
        when(quickReservationAdventureService.fishingInstructorNotFree("slavko@gmail.com", startDate, endDate)).thenReturn(false);

        when(adventureReservationCancellationRepository.clientHasCancellationWithInstructorInPeriod(1L, 1L, startDate, endDate)).thenReturn(false);
        when(adventureReservationCancellationRepository.clientHasCancellationWithInstructorInPeriod(2L, 1L, startDate, endDate)).thenReturn(false);
        when(adventureReservationCancellationService.clientHasCancellationWithInstructorInPeriod("mirko@gmail.com", "testUser", startDate, endDate)).thenReturn(false);
        when(adventureReservationCancellationService.clientHasCancellationWithInstructorInPeriod("slavko@gmail.com", "testUser", startDate, endDate)).thenReturn(false);

        when(adventureService.findAdventuresByInstructorIds(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(adventure1, adventure2, adventure3, adventure4));

        when(clientService.findByUsername("testUser")).thenReturn(new Client(1L, "testUser"));
        when(clientRepository.findByID(1L)).thenReturn(new Client(1L, "testUser"));

        assertThat(adventureReservationService.getAvailableAdventures(new SearchAvailablePeriodsBoatAndAdventureDto(startDate, endDate, 100.0,"testUser", 4.0, "", "", "", 5))).hasSize(3);
    }

    @Test
    public void testSearchAvailableAdventures(){
        Address address1 = new Address(53,43,"Serbia","Novi Sad","Dunavska");
        Address address2 = new Address(53,43,"Serbia","Belgrade","Kralja Milana");
        FishingInstructor fishingInstructor1 = new FishingInstructor(1L,"Miroslav","Mirkovic","mirko@gmail.com","123","061435234",address1,new HashSet<>(),new HashSet<>(),"bla bla");
        FishingInstructor fishingInstructor2 = new FishingInstructor(2L,"Slavko","Slavkovic","slavko@gmail.com","123","061435234",address2,new HashSet<>(),new HashSet<>(),"bla bla");
        fishingInstructor1.setRating(4.5);
        fishingInstructor2.setRating(4.0);
        Adventure adventure1 = new Adventure(1L,"Fishing adventure1",address1,"The best.","Licenced instructor.",3,40,"No kids.","Hooks and rails","FREE");
        Adventure adventure2 = new Adventure(2L,"Fishing adventure2",address2,"The best.","Licenced instructor.",5,60,"No kids.","Hooks and rails","FREE");
        Adventure adventure3 = new Adventure(3L,"Fishing adventure3",address1,"The best.","Licenced instructor.",7,80,"No kids.","Hooks and rails","FREE");
        Adventure adventure4 = new Adventure(4L,"Fishing adventure4",address2,"The best.","Licenced instructor.",9,100,"No kids.","Hooks and rails","FREE");
        adventure1.setFishingInstructor(fishingInstructor1);
        adventure2.setFishingInstructor(fishingInstructor1);
        adventure3.setFishingInstructor(fishingInstructor2);
        adventure4.setFishingInstructor(fishingInstructor2);

        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(2);

        when(availableInstructorPeriodService.getAvailableFishingInstructorsIdsForPeriod(startDate, endDate)).thenReturn(Arrays.asList(1L, 2L));
        when(fishingInstructorRepository.findByID(1L)).thenReturn(fishingInstructor1);
        when(fishingInstructorRepository.findByID(2L)).thenReturn(fishingInstructor2);

        when(adventureReservationRepository.instructorHasReservationInPeriod("mirko@gmail.com", startDate, endDate)).thenReturn(false);
        when(quickReservationAdventureService.fishingInstructorNotFree("mirko@gmail.com", startDate, endDate)).thenReturn(false);
        when(adventureReservationRepository.instructorHasReservationInPeriod("slavko@gmail.com", startDate, endDate)).thenReturn(false);
        when(quickReservationAdventureService.fishingInstructorNotFree("slavko@gmail.com", startDate, endDate)).thenReturn(false);

        when(adventureReservationCancellationRepository.clientHasCancellationWithInstructorInPeriod(1L, 1L, startDate, endDate)).thenReturn(false);
        when(adventureReservationCancellationRepository.clientHasCancellationWithInstructorInPeriod(2L, 1L, startDate, endDate)).thenReturn(false);

        when(adventureService.findAdventuresByInstructorIds(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(adventure1, adventure2, adventure3, adventure4));

        when(clientService.findByUsername("testUser")).thenReturn(new Client(1L, "testUser"));
        when(clientRepository.findByID(1L)).thenReturn(new Client(1L, "testUser"));

        assertThat(adventureReservationService.searchAvailableAdventures(new SearchAvailablePeriodsBoatAndAdventureDto(startDate, endDate, 100.0,"testUser", 4.2, "", "Novi Sad", "", 3))).hasSize(1);
    }
}
