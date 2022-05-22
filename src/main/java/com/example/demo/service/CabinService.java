package com.example.demo.service;

import com.example.demo.model.AdditionalServices;
import com.example.demo.model.Cabin;
import com.example.demo.model.Image;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Set;

public interface CabinService {

    Cabin findById(Long id);
    List<Cabin> findAll();
    @Cacheable("cabin")
    Cabin findByName(String cabin);
    boolean save(Cabin cabin, Set<AdditionalServices> additionalServices);

    void addNewImage(String cabinName, Image image);

    Set<Cabin> findByOwnersId(Long id);

    void delete(Long id);

    Cabin edit(Cabin cabin, Boolean deleteOldImages);
    boolean canBeEditedOrDeleted(Long id);


    Double findAvgCabinRatingByOwnerId(Long ownerId);

    void updateCabinGrade(Long cabinId);
}
