package com.example.demo.service;

import com.example.demo.model.CabinOwner;

import java.util.List;

public interface CabinOwnerService {

    CabinOwner findByUsername(String ownerUsername);
}
