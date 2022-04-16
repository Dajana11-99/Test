package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.CabinSubscription;
import rs.ac.uns.ftn.isa.fisherman.model.Evaluation;

import java.util.List;
import java.util.Set;

public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {

    @Query(value="SELECT * FROM evaluations where approved=false",nativeQuery = true)
    List<Evaluation> getAll();

    @Query(value="SELECT * FROM evaluations where id=:id",nativeQuery = true)
    Evaluation getById(@Param("id")Long id);
}
