package com.example.demo.repository;

import com.example.demo.model.BoatOwnerComplaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoatOwnerComplaintRepository extends JpaRepository<BoatOwnerComplaint, Long> {
    @Query(value="SELECT *  FROM complaints c where id=:id",nativeQuery = true)
    BoatOwnerComplaint getById(@Param("id") Long id);
}
