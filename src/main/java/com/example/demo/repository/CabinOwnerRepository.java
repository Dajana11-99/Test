package com.example.demo.repository;

import com.example.demo.model.CabinOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CabinOwnerRepository extends JpaRepository<CabinOwner,Integer> {

    @Query(value="SELECT * FROM users where username=:username",nativeQuery = true)
    CabinOwner findByUsername(@Param("username")String username);
}
