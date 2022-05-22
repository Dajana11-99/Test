package com.example.demo.service;


import com.example.demo.model.AdditionalServices;

import java.util.List;
import java.util.Set;

public interface AdditionalServicesService {
    List<AdditionalServices> getAll();
    AdditionalServices findById(Long id);
    void delete(Set<AdditionalServices> additionalServices);
    Set<AdditionalServices> findDeletedAdditionalServices(Set<AdditionalServices> oldServices,Set<AdditionalServices> newServices);
}
