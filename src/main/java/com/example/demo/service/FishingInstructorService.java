package com.example.demo.service;

import com.example.demo.model.FishingInstructor;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface FishingInstructorService {
    FishingInstructor findByUsername(String fishingInstructorUsername);

    @Cacheable("instructor")
    FishingInstructor findByID(Long id);

    void save(FishingInstructor fishingInstructor);
}
