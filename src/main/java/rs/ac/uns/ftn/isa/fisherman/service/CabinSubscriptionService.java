package rs.ac.uns.ftn.isa.fisherman.service;

import java.util.Set;

public interface CabinSubscriptionService {

    void addSubscription(String clientUsername, Long cabinId);
    void removeSubscription(String username, Long cabinId);
    boolean checkIfUserIsSubscribed(String username, Long cabinId);

    Set<String> findCabinSubscribers(Long cabinId);
}
