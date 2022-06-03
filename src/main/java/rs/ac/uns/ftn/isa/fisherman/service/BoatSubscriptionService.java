package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.BoatSubscription;

import java.util.Set;

public interface BoatSubscriptionService {
    void addSubscription(String clientUsername, Long boatId) throws Exception;
    void removeSubscription(String username, Long boatId);
    boolean checkIfUserIsSubscribed(String username, Long boatId);
    Set<BoatSubscription> findSubscriptionsByClientUsername(String username);
    Set<String> findBoatSubscribers(Long boatId);
}
