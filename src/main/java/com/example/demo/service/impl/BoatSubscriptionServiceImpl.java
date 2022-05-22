package com.example.demo.service.impl;

import com.example.demo.model.BoatSubscription;
import com.example.demo.model.Client;
import com.example.demo.repository.BoatSubscriptionRepository;
import com.example.demo.service.BoatService;
import com.example.demo.service.BoatSubscriptionService;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addSubscription(String clientUsername, Long boatId) {
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
