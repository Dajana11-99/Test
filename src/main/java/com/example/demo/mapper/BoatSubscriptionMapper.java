package com.example.demo.mapper;


import com.example.demo.dto.BoatSubscriptionDto;
import com.example.demo.model.BoatSubscription;

public class BoatSubscriptionMapper {

    final private BoatMapper boatMapper = new BoatMapper();

    public BoatSubscriptionDto boatSubscriptionToBoatSubscriptionDto(BoatSubscription boatSubscription) {
        return new BoatSubscriptionDto(boatSubscription.getId(), boatSubscription.getClient().getUsername(), boatMapper.boatToBoatDto(boatSubscription.getBoat()));
    }
}
