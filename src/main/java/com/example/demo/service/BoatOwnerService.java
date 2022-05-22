package com.example.demo.service;



import com.example.demo.model.BoatOwner;

import java.util.List;

public interface BoatOwnerService {
    BoatOwner findByUsername(String ownersUsername);
    void save(BoatOwner boatOwner);
}
