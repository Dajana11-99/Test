package com.example.demo.repository;

import com.example.demo.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Set;


public interface AdventureRepository extends JpaRepository<Adventure, Long> {

    Adventure findByName(String adventureName);

    @Query(value = "SELECT * FROM adventure WHERE users_id=:users_id",nativeQuery = true)
    Set<Adventure> findAdventuresByInstructorId(@Param("users_id")Long id);

    @Query(value = "SELECT * FROM adventure WHERE users_id=:users_id and name=:name",nativeQuery = true)
    Adventure findAdventureByName(@Param("name")String name, @Param("users_id")Long usersId);

    @Query(value = "SELECT * FROM adventure WHERE id=:id",nativeQuery = true)
    Adventure findByID(@Param("id")Long id);

    @Query(value = "SELECT * FROM adventure WHERE users_id in :ids",nativeQuery = true)
    List<Adventure> findAdventuresByInstructorIds(@Param("ids")List<Long> instructorIds);
}
