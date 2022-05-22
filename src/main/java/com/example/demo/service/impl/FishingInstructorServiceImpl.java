package com.example.demo.service.impl;

import com.example.demo.model.FishingInstructor;
import com.example.demo.repository.FishingInstructorRepository;
import com.example.demo.service.FishingInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishingInstructorServiceImpl implements FishingInstructorService {
    private FishingInstructorRepository fishingInstructorRepository;
    @Autowired
    public FishingInstructorServiceImpl(FishingInstructorRepository fishingInstructorRepository) {
        this.fishingInstructorRepository = fishingInstructorRepository;
    }


    @Override
    public FishingInstructor findByUsername(String fishingInstructorUsername) {
       return  fishingInstructorRepository.findByUsername(fishingInstructorUsername);
    }

    @Override
    public FishingInstructor findByID(Long id) {
        return  fishingInstructorRepository.findByID(id);
    }

    @Override
    public void save(FishingInstructor fishingInstructor) {
        fishingInstructorRepository.save(fishingInstructor);
    }


}
