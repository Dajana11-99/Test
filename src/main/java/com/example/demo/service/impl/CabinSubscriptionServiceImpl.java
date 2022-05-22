package com.example.demo.service.impl;

import com.example.demo.model.CabinSubscription;
import com.example.demo.model.Client;
import com.example.demo.repository.CabinSubscriptionRepository;
import com.example.demo.service.CabinService;
import com.example.demo.service.CabinSubscriptionService;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        CabinSubscription cabinSubscription = cabinSubscriptionRepository.getSubscriptionOnCabin(cabinId, clientService.findByUsername(username).getId());
        cabinSubscriptionRepository.deleteById(cabinSubscription.getId());
    }

    @Override
    public boolean checkIfUserIsSubscribed(String username, Long cabinId) {
        return cabinSubscriptionRepository.subscriptionExists(cabinId, clientService.findByUsername(username).getId());
    }

    @Override
    public Set<CabinSubscription> findSubscriptionsByClientUsername(String username) {
        return cabinSubscriptionRepository.getClientSubscriptions(clientService.findByUsername(username).getId());
    }

    @Override
    public Set<String> findCabinSubscribers(Long cabinId) {
        return cabinSubscriptionRepository.findCabinSubscribers(cabinId);
    }
}
