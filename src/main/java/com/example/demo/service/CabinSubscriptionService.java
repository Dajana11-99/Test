package com.example.demo.service;

import com.example.demo.model.CabinSubscription;

import java.util.Set;

public interface CabinSubscriptionService {

    void addSubscription(String clientUsername, Long cabinId);
    void removeSubscription(String username, Long cabinId);
    boolean checkIfUserIsSubscribed(String username, Long cabinId);
    Set<CabinSubscription> findSubscriptionsByClientUsername(String username);
    Set<String> findCabinSubscribers(Long cabinId);
}