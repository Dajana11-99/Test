package com.example.demo.service;


import com.example.demo.dto.AddNewEvaluationDto;
import com.example.demo.model.CabinEvaluation;

import java.util.Set;

public interface CabinEvaluationService {

    void addEvaluation(AddNewEvaluationDto addNewEvaluationDto);

    Set<CabinEvaluation> findByCabinId(Long cabinId);

    CabinEvaluation getById(Long id);
}
