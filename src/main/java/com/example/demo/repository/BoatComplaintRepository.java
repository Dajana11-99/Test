package com.example.demo.repository;

import com.example.demo.model.BoatComplaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoatComplaintRepository extends JpaRepository<BoatComplaint, Long> {
    @Query(value="SELECT *  FROM complaints c where id=:id",nativeQuery = true)
    BoatComplaint getById(@Param("id") Long id);
}
