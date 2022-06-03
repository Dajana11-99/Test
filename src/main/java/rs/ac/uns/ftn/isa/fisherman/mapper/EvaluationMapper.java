package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.EvaluationDto;
import rs.ac.uns.ftn.isa.fisherman.model.Evaluation;

public class EvaluationMapper {

    public EvaluationDto evaluationToDto(Evaluation evaluation){
        return new EvaluationDto(evaluation.getId(),evaluation.getType(),evaluation.getDate(),evaluation.getComment(),evaluation.getGrade(),evaluation.isApproved(),evaluation.getClient().getUsername(),evaluation.getOwnersUsername());
    }
}
