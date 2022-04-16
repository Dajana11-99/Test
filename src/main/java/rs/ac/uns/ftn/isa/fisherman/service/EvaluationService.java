package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.Evaluation;

import java.util.List;

public interface EvaluationService {
    List<Evaluation> getAll();

    void setEvaluationStatus(Long id);

    void deleteUnapprovedEvaluation(Long id);
}
