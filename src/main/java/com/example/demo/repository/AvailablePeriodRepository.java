package com.example.demo.repository;

import com.example.demo.model.AvailablePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AvailablePeriodRepository extends JpaRepository<AvailablePeriod, Long> {
    @Query(value = "select * from available_period where id=:id",nativeQuery = true)
    AvailablePeriod findByPeriodId(@Param("id")Long id);
}
