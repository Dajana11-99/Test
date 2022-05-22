package com.example.demo.repository;
import com.example.demo.model.AvailableBoatPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface AvailableBoatPeriodRepository extends JpaRepository<AvailableBoatPeriod,Long> {
    @Query(value="SELECT * FROM available_period where boat_id=:boat_id",nativeQuery = true)
    Set<AvailableBoatPeriod> findByBoatId(@Param("boat_id")Long boatId);

    @Query(value="SELECT * FROM available_period ap where boat_id=:boat_id and ((:start between start_date and end_date) or (:end between start_date and end_date) or (start_date between :start and :end) or (end_date between :start and :end))",nativeQuery = true)
    List<AvailableBoatPeriod> availablePeriodAlreadyExists(@Param("boat_id")Long id,@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);

    @Query(value="SELECT * FROM available_period ap where boat_id=:boat_id and ((:start between start_date and end_date)) and ((:end between start_date and end_date))",nativeQuery = true)
    List<AvailableBoatPeriod> boatIsAvailable(@Param("boat_id")Long boatId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query(value="SELECT * FROM available_period where boat_id=:boat_id and start_date=:startDate and end_date=:endDate",nativeQuery = true)
    AvailableBoatPeriod findId(@Param("boat_id")Long id, @Param("startDate")LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);

    @Query(value="SELECT CASE WHEN  COUNT(ap) > 0 THEN true ELSE false END FROM available_period ap where id=:id and ((:startDate between start_date and end_date) and (:endDate between start_date and end_date))",nativeQuery = true)
    boolean availablePeriodIncludesUnavailable(@Param("id")Long id, @Param("startDate")LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(value="SELECT * FROM available_period where start_date<=:startDate and end_date>=:endDate and type='BoatPeriod'",nativeQuery = true)
    Set<AvailableBoatPeriod> findPeriodsBetweenDates(@Param("startDate")LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
