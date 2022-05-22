package com.example.demo.mapper;


import com.example.demo.dto.CabinEvaluationDto;
import com.example.demo.model.CabinEvaluation;

public class CabinEvaluationMapper {
    private CabinMapper cabinMapper=new CabinMapper();
    public CabinEvaluationDto cabinEvaluationToDto(CabinEvaluation cabinEvaluation){
        return new CabinEvaluationDto(cabinEvaluation.getId(),null,cabinEvaluation.getDate(),cabinEvaluation.getComment(),
                cabinEvaluation.getGrade(),cabinEvaluation.isApproved(),cabinEvaluation.getClient().getUsername(),cabinEvaluation.getOwnersUsername(),
                cabinMapper.cabinToCabinDto(cabinEvaluation.getCabin()), null);
    }
}
