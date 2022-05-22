package com.example.demo.repository;

import com.example.demo.model.OwnersReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface OwnersReportRepository extends JpaRepository<OwnersReport,Long> {

    @Query(value = "SELECT * FROM owner_reports where approved=false and bad_comment=true", nativeQuery = true)
    Set<OwnersReport> getAllUnApprovedReports();

    @Query(value = "SELECT * FROM owner_reports where id=:id", nativeQuery = true)
    OwnersReport getById(Long id);



}
