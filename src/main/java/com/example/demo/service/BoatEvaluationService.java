package com.example.demo.service;

import com.example.demo.dto.AddNewEvaluationDto;
import com.example.demo.model.BoatEvaluation;

public interface BoatEvaluationService {

    void addEvaluation(AddNewEvaluationDto addNewEvaluationDto);

    BoatEvaluation findById(Long Id);

}
