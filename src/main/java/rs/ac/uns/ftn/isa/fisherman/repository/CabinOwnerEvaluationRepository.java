package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwnerEvaluation;

public interface CabinOwnerEvaluationRepository extends JpaRepository<CabinOwnerEvaluation,Integer> {

}
