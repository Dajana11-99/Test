package com.example.demo.service.impl;

import com.example.demo.repository.AvailablePeriodRepository;
import com.example.demo.service.AvailablePeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailablePeriodServiceImpl implements AvailablePeriodService {
    @Autowired
    private AvailablePeriodRepository availablePeriodRepository;
    @Override
    public void deleteAvailablePeriod(Long id) {
        availablePeriodRepository.deleteById(id);
    }
}
