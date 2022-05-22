package com.example.demo.repository;

import com.example.demo.model.FishingInstructorComplaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FishingInstructorComplaintRepository extends JpaRepository<FishingInstructorComplaint, Long> {
    @Query(value="SELECT *  FROM complaints c where id=:id",nativeQuery = true)
    FishingInstructorComplaint getById(@Param("id") Long id);
}
