package com.example.demo.repository;

import com.example.demo.model.BoatEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoatEvaluationRepository extends JpaRepository<BoatEvaluation, Long> {

    @Query(value="SELECT *  FROM evaluations c where id=:id",nativeQuery = true)
    BoatEvaluation getById(@Param("id") Long id);
}
