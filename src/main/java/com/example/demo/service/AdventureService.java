package com.example.demo.service;

import com.example.demo.model.AdditionalServices;
import com.example.demo.model.Adventure;
import com.example.demo.model.Image;

import java.util.List;
import java.util.Set;

public interface AdventureService {

    void save(Adventure adventure);
    Adventure findByName(String adventureName);

    void addNewImage(String adventureName, Image image);
    
    Set<Adventure> findAdventuresByInstructorId(Long id);
    Adventure findAdventureByName(String adventureName, Long fishingInstructorId);

    boolean delete(Long id);

    boolean edit(Adventure adventure, Long id) throws Exception;
    boolean canBeEditedOrDeleted(Long id);
    List<Adventure> findAll();
    boolean addNewAdventure(Adventure adventure,Set<AdditionalServices>additionalServices);
    Adventure findById(Long id);
    List<Adventure> findAdventuresByInstructorIds(List<Long> instructorIds);
}
