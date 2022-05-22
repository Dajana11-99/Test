package com.example.demo.repository;

import com.example.demo.model.AdditionalServices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalServicesRepository extends JpaRepository<AdditionalServices,Integer> {
    AdditionalServices findById(Long id);
}
