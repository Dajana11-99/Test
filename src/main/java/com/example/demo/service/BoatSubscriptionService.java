package com.example.demo.service;



import com.example.demo.model.BoatSubscription;

import java.util.Set;

public interface BoatSubscriptionService {
    void addSubscription(String clientUsername, Long boatId);
    void removeSubscription(String username, Long boatId);
    boolean checkIfUserIsSubscribed(String username, Long boatId);
    Set<BoatSubscription> findSubscriptionsByClientUsername(String username);
    Set<String> findBoatSubscribers(Long boatId);
}
