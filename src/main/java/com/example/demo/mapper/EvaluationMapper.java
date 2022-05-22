package com.example.demo.mapper;


import com.example.demo.dto.EvaluationDto;
import com.example.demo.model.Evaluation;

public class EvaluationMapper {

    public EvaluationDto evaluationToDto(Evaluation evaluation){
        return new EvaluationDto(evaluation.getId(),evaluation.getType(),evaluation.getDate(),evaluation.getComment(),evaluation.getGrade(),evaluation.isApproved(),evaluation.getClient().getUsername(),evaluation.getOwnersUsername());
    }
}
