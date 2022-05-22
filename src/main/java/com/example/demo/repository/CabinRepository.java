package com.example.demo.repository;
import com.example.demo.model.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface CabinRepository extends JpaRepository<Cabin,Integer> {
    Cabin findById(Long id);
    Cabin findByName(String cabin);

    @Query(value = "SELECT * FROM cabin WHERE users_id=:users_id",nativeQuery = true)
    Set<Cabin> findByOwnersId(@Param("users_id")Long id);

    @Query(value = "SELECT id FROM cabin WHERE users_id=:users_id",nativeQuery = true)
    Set<Integer> findCabinsIdByOwnersId(@Param("users_id")Long id);

    @Query(value = "SELECT avg(rating) FROM cabin WHERE users_id=:users_id",nativeQuery = true)
    Double findAvgCabinRatingByOwnerId(@Param("users_id")Long id);
}
