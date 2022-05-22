package com.example.demo.repository;

import com.example.demo.model.CabinOwnerComplaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CabinOwnerComplaintRepository  extends JpaRepository<CabinOwnerComplaint, Long> {

    @Query(value="SELECT *  FROM complaints c where id=:id",nativeQuery = true)
    CabinOwnerComplaint getById(@Param("id") Long id);
}
