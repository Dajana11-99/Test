package com.example.demo.service.impl;

import com.example.demo.model.AdventureSubscription;
import com.example.demo.model.Client;
import com.example.demo.repository.AdventureSubscriptionRepository;
import com.example.demo.service.AdventureService;
import com.example.demo.service.AdventureSubscriptionService;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdventureSubscriptionServiceImpl implements AdventureSubscriptionService {

    @Autowired
    private ClientService clientService;
    @Autowired
    private AdventureService adventureService;
    @Autowired
    private AdventureSubscriptionRepository adventureSubscriptionRepository;

    @Override
    public void addSubscription(String clientUsername, Long adventureId) {
        Client client = clientService.findByUsername(clientUsername);
        if(adventureSubscriptionRepository.subscriptionExists(adventureId, client.getId()))
            return;
        adventureSubscriptionRepository.save(new AdventureSubscription(null, client, adventureService.findById(adventureId)));
    }

    @Override
    public void removeSubscription(String username, Long adventureId) {
        AdventureSubscription adventureSubscription = adventureSubscriptionRepository.getSubscriptionOnAdventure(adventureId, clientService.findByUsername(username).getId());
        adventureSubscriptionRepository.deleteById(adventureSubscription.getId());
    }

    @Override
    public boolean checkIfUserIsSubscribed(String username, Long adventureId) {
        return adventureSubscriptionRepository.subscriptionExists(adventureId, clientService.findByUsername(username).getId());
    }

   @Override
    public Set<AdventureSubscription> findSubscriptionsByClientUsername(String username) {
        Long clientId = clientService.findByUsername(username).getId();
        Set<AdventureSubscription> subscriptions = new HashSet<>();
        for(AdventureSubscription subscription:adventureSubscriptionRepository.findAll())
            if(subscription.getClient().getId().equals(clientId))
                subscriptions.add(subscription);
        return subscriptions;
    }

    @Override
    public Set<String> findAdventureSubscribers(Long adventureId) {
        return adventureSubscriptionRepository.findAdventureSubscribers(adventureId);
    }
}
