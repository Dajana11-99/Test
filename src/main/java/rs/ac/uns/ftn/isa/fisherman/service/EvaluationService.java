package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.Evaluation;

import java.util.List;

public interface EvaluationService {
    List<Evaluation> getAll();

    void setEvaluationStatus(Long id);

    void deleteUnapprovedEvaluation(Long id);

    List<Evaluation> findCabinOwnerEvaluations(Long id);

    List<Evaluation> findBoatOwnerEvaluations(Long id);

    List<Evaluation>  findInstructorEvaluations(Long id);

    List<Evaluation>  getBoatEvaluations(Long boatId);
}
