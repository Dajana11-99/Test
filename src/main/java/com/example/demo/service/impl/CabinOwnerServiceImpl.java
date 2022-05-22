package com.example.demo.service.impl;
import com.example.demo.model.CabinOwner;
import com.example.demo.repository.CabinOwnerRepository;
import com.example.demo.service.CabinOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabinOwnerServiceImpl implements CabinOwnerService {
    private CabinOwnerRepository cabinOwnerRepository;
    @Autowired
    public CabinOwnerServiceImpl(CabinOwnerRepository cabinOwnerRepository) {
        this.cabinOwnerRepository = cabinOwnerRepository;
    }


    @Override
    public CabinOwner findByUsername(String username) {
        return cabinOwnerRepository.findByUsername(username);
    }

}
