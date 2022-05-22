package com.example.demo.repository;

import com.example.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface ClientRepository extends JpaRepository<Client,Integer> {

    @Query(value="SELECT * FROM users where username=:username",nativeQuery = true)
    Client findByUsername(@Param("username")String username);
}
