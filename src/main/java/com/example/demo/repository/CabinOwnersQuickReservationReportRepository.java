package com.example.demo.repository;

import com.example.demo.model.CabinQuickReservationReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabinOwnersQuickReservationReportRepository extends JpaRepository<CabinQuickReservationReport,Long> {
}
