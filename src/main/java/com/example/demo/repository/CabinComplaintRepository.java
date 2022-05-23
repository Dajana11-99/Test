package com.example.demo.repository;

import com.example.demo.model.CabinComplaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CabinComplaintRepository extends JpaRepository<CabinComplaint, Long> {

    @Query(value="SELECT *  FROM complaints c where id=:id",nativeQuery = true)
    CabinComplaint getById(@Param("id") Long id);
}