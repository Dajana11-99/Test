package com.example.demo.service.impl;
import com.example.demo.model.BoatOwner;
import com.example.demo.repository.BoatOwnerRepository;
import com.example.demo.service.BoatOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoatOwnerServiceImpl implements BoatOwnerService {
    private BoatOwnerRepository boatOwnerRepository;
    @Autowired
    public BoatOwnerServiceImpl(BoatOwnerRepository boatOwnerRepository) {
        this.boatOwnerRepository = boatOwnerRepository;
    }


    @Override
    public BoatOwner findByUsername(String ownersUsername) {
        return boatOwnerRepository.findByUsername(ownersUsername);
    }

    @Override
    public void save(BoatOwner boatOwner) {
        boatOwnerRepository.save(boatOwner);
    }




}
