package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.BoatEvaluation;
import rs.ac.uns.ftn.isa.fisherman.model.CabinEvaluation;

import java.util.Set;

public interface BoatEvaluationRepository extends JpaRepository<BoatEvaluation, Long> {

    @Query(value="SELECT *  FROM evaluations c where id=:id",nativeQuery = true)
    BoatEvaluation getById(@Param("id") Long id);

    @Query(value="SELECT grade FROM evaluations c where boat_id=:boat_id and approved=true",nativeQuery = true)
    Set<Double> getAllApprovedBoatEvaluationsByBoatId(@Param("boat_id") Long boatId);
}
