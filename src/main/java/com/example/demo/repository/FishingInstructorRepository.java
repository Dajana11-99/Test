package com.example.demo.repository;

import com.example.demo.model.FishingInstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FishingInstructorRepository extends JpaRepository<FishingInstructor,Long> {
    @Query(value="SELECT * FROM users where username=:username",nativeQuery = true)
    FishingInstructor findByUsername(@Param("username")String username );

    @Query(value="SELECT * FROM users where id=:id",nativeQuery = true)
    FishingInstructor findByID(@Param("id")Long id);
}
