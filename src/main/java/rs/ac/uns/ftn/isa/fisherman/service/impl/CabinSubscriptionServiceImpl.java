package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.CabinSubscription;
import rs.ac.uns.ftn.isa.fisherman.model.Client;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinSubscriptionRepository;
import rs.ac.uns.ftn.isa.fisherman.service.CabinService;
import rs.ac.uns.ftn.isa.fisherman.service.CabinSubscriptionService;
import rs.ac.uns.ftn.isa.fisherman.service.ClientService;

import java.util.Set;


@Service
public class CabinSubscriptionServiceImpl implements CabinSubscriptionService {

    @Autowired
    private ClientService clientService;
    @Autowired
    private CabinService cabinService;
    @Autowired
    private CabinSubscriptionRepository cabinSubscriptionRepository;

    @Override
    public void addSubscription(String clientUsername, Long cabinId) {
        Client client = clientService.findByUsername(clientUsername);
        if(cabinSubscriptionRepository.subscriptionExists(cabinId, client.getId()))
            return;
        CabinSubscription cabinSubscription = new CabinSubscription();
        cabinSubscription.setCabin(cabinService.findById(cabinId));
        cabinSubscription.setClient(client);
        cabinSubscriptionRepository.save(cabinSubscription);
    }

    @Override
    public void removeSubscription(String username, Long cabinId) {
        Client client = clientService.findByUsername(username);
        CabinSubscription cabinSubscription = cabinSubscriptionRepository.getSubscriptionOnCabin(cabinId, client.getId());
        cabinSubscriptionRepository.deleteById(cabinSubscription.getId());
    }

    @Override
    public boolean checkIfUserIsSubscribed(String username, Long cabinId) {
        Client client = clientService.findByUsername(username);
        if(cabinSubscriptionRepository.subscriptionExists(cabinId, client.getId()))
            return true;
        return false;
    }

    @Override
    public Set<String> findCabinSubscribers(Long cabinId) {
        return cabinSubscriptionRepository.findCabinSubscribers(cabinId);
    }
}
