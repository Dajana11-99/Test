package rs.ac.uns.ftn.isa.fisherman.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.isa.fisherman.model.Adventure;
import rs.ac.uns.ftn.isa.fisherman.model.AdventureSubscription;
import rs.ac.uns.ftn.isa.fisherman.model.Client;
import rs.ac.uns.ftn.isa.fisherman.repository.AdventureSubscriptionRepository;
import rs.ac.uns.ftn.isa.fisherman.service.impl.AdventureSubscriptionServiceImpl;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdventureSubscriptionServiceTest {

    @Mock
    private AdventureSubscriptionRepository adventureSubscriptionRepository;
    @Mock
    private ClientService clientService;
    @InjectMocks
    private AdventureSubscriptionServiceImpl adventureSubscriptionService;

    @Test
    public void testFindSubscriptionsByClientUsername(){
        Client client1 = new Client(1L, "testUser");
        Client client2 = new Client(2L, "testUser2nd");
        Client client3 = new Client(3L, "testUser3rd");

        when(adventureSubscriptionRepository.findAll()).thenReturn(Arrays.asList(
                new AdventureSubscription(1L, client1, new Adventure()),
                new AdventureSubscription(2L, client2, new Adventure()),
                new AdventureSubscription(3L, client1, new Adventure())
        ));
        when(clientService.findByUsername("testUser")).thenReturn(client1);
        when(clientService.findByUsername("testUser2nd")).thenReturn(client2);
        when(clientService.findByUsername("testUser3rd")).thenReturn(client3);

        assertThat(adventureSubscriptionService.findSubscriptionsByClientUsername("testUser")).hasSize(2);
        assertThat(adventureSubscriptionService.findSubscriptionsByClientUsername("testUser2nd")).hasSize(1);
        assertThat(adventureSubscriptionService.findSubscriptionsByClientUsername("testUser3rd")).isEmpty();
    }
}
