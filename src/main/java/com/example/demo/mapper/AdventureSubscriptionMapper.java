package com.example.demo.mapper;


import com.example.demo.dto.AdventureSubscriptionDto;
import com.example.demo.model.AdventureSubscription;

public class AdventureSubscriptionMapper {
    final private AdventureMapper adventureMapper = new AdventureMapper();
    public AdventureSubscriptionDto adventureSubscriptionToAdventureSubscriptionDto(AdventureSubscription adventureSubscription) {
        return new AdventureSubscriptionDto(adventureSubscription.getId(), adventureSubscription.getClient().getUsername(), adventureMapper.adventureToAdventureDto(adventureSubscription.getAdventure()));
    }
}
