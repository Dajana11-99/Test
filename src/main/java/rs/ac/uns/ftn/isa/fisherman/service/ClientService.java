package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.Client;

public interface ClientService {

    Client findByUsername(String clientUsername);

}
