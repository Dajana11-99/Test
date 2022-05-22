package com.example.demo.service;


import com.example.demo.model.AdventureSubscription;

import java.util.Set;

public interface AdventureSubscriptionService {
    void addSubscription(String clientUsername, Long adventureId);
    void removeSubscription(String username, Long adventureId);
    boolean checkIfUserIsSubscribed(String username, Long adventureId);
    Set<AdventureSubscription> findSubscriptionsByClientUsername(String username);
    Set<String> findAdventureSubscribers(Long adventureId);
}
