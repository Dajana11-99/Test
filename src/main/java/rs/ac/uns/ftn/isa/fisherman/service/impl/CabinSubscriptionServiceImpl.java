package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.fisherman.model.CabinSubscription;
import rs.ac.uns.ftn.isa.fisherman.model.Client;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinSubscriptionRepository;
import rs.ac.uns.ftn.isa.fisherman.service.CabinService;
import rs.ac.uns.ftn.isa.fisherman.service.CabinSubscriptionService;
import rs.ac.uns.ftn.isa.fisherman.service.ClientService;

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
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void addSubscription(String clientUsername, Long cabinId) throws Exception{
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
