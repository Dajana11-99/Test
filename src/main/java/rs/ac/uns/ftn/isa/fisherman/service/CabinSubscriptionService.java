package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.CabinSubscription;

import java.util.Set;

public interface CabinSubscriptionService {

    void addSubscription(String clientUsername, Long cabinId) throws Exception;
    void removeSubscription(String username, Long cabinId);
    boolean checkIfUserIsSubscribed(String username, Long cabinId);
    Set<CabinSubscription> findSubscriptionsByClientUsername(String username);
    Set<String> findCabinSubscribers(Long cabinId);
}
