package com.example.demo.repository;

import com.example.demo.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ComplaintRepository  extends JpaRepository<Complaint, Long> {

    @Query(value="SELECT * FROM complaints where responded=false",nativeQuery = true)
    List<Complaint> getAll();

    @Query(value="SELECT * FROM complaints where id=:id",nativeQuery = true)
    Complaint getById(Long id);


}
