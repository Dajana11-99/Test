package com.example.demo.repository;
import com.example.demo.model.BoatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoatOwnerRepository extends JpaRepository<BoatOwner,Integer> {

    @Query(value="SELECT * FROM users where username=:username",nativeQuery = true)
    BoatOwner findByUsername(@Param("username")String username);



}
