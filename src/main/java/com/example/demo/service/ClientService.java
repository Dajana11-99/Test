package com.example.demo.service;


import com.example.demo.model.Client;

public interface ClientService {

    Client findByUsername(String clientUsername);

}
