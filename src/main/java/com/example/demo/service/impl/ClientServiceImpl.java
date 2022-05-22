package com.example.demo.service.impl;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client findByUsername(String clientUsername) {
        return clientRepository.findByUsername(clientUsername);
    }
}
