package com.example.demo.mapper;


import com.example.demo.dto.CabinSubscriptionDto;
import com.example.demo.model.CabinSubscription;

public class CabinSubscriptionMapper {

    final private CabinMapper cabinMapper = new CabinMapper();

    public CabinSubscriptionDto cabinSubscriptionToCabinSubscriptionDto(CabinSubscription cabinSubscription) {
        return new CabinSubscriptionDto(cabinSubscription.getId(), cabinSubscription.getClient().getUsername(), cabinMapper.cabinToCabinDto(cabinSubscription.getCabin()));
    }
}
