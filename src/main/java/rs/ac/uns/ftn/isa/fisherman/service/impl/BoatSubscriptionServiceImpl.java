package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.fisherman.model.BoatSubscription;
import rs.ac.uns.ftn.isa.fisherman.model.Client;
import rs.ac.uns.ftn.isa.fisherman.repository.BoatSubscriptionRepository;
import rs.ac.uns.ftn.isa.fisherman.service.BoatService;
import rs.ac.uns.ftn.isa.fisherman.service.BoatSubscriptionService;
import rs.ac.uns.ftn.isa.fisherman.service.ClientService;

import java.util.Set;

@Service
public class BoatSubscriptionServiceImpl implements BoatSubscriptionService {

    @Autowired
    private ClientService clientService;
    @Autowired
    private BoatService boatService;
    @Autowired
    private BoatSubscriptionRepository boatSubscriptionRepository;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void addSubscription(String clientUsername, Long boatId) throws Exception{
        Client client = clientService.findByUsername(clientUsername);
        if(boatSubscriptionRepository.subscriptionExists(boatId, client.getId()))
            return;
        boatSubscriptionRepository.save(new BoatSubscription(null, client, boatService.findById(boatId)));
    }

    @Override
    public void removeSubscription(String username, Long boatId) {
        BoatSubscription boatSubscription = boatSubscriptionRepository.getSubscriptionOnBoat(boatId, clientService.findByUsername(username).getId());
        boatSubscriptionRepository.deleteById(boatSubscription.getId());
    }

    @Override
    public boolean checkIfUserIsSubscribed(String username, Long boatId) {
        return boatSubscriptionRepository.subscriptionExists(boatId, clientService.findByUsername(username).getId());
    }

    @Override
    public Set<BoatSubscription> findSubscriptionsByClientUsername(String username) {
        return boatSubscriptionRepository.findSubscriptionsByClientId(clientService.findByUsername(username).getId());
    }

    @Override
    public Set<String> findBoatSubscribers(Long boatId) {
        return boatSubscriptionRepository.findCabinSubscribers(boatId);
    }
}
