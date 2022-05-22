package com.example.demo.repository;

import com.example.demo.model.CabinEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface CabinEvaluationRepository extends JpaRepository<CabinEvaluation, Long> {

    @Query(value="SELECT CASE WHEN  COUNT(c) > 0 THEN true ELSE false END FROM evaluations c where cabin_reservation_id=:cabin_reservation_id",nativeQuery = true)
    boolean reservationHasEvaluation(@Param("cabin_reservation_id")Long cabin_reservation_id);

    @Query(value="SELECT grade  FROM evaluations c where cabin_id in :cabin_reservation_ids and approved=true",nativeQuery = true)
    Set<Double> getAllApprovedCabinEvaluationsByCabinReservationIds(@Param("cabin_reservation_ids") Set<Integer> cabin_reservation_ids);


    @Query(value="SELECT *  FROM evaluations c where id=:id",nativeQuery = true)
    CabinEvaluation getById(@Param("id") Long id);

    @Query(value="SELECT *  FROM evaluations c where cabin_id=:id",nativeQuery = true)
    Set<CabinEvaluation> findByCabinId(@Param("id")Long cabinId);
}
