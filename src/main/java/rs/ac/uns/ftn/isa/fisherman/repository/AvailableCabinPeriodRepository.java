package rs.ac.uns.ftn.isa.fisherman.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableCabinPeriod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface AvailableCabinPeriodRepository extends JpaRepository<AvailableCabinPeriod, Long> {
    @Query(value="SELECT * FROM available_period where cabin_id=:cabin_id",nativeQuery = true)
    Set<AvailableCabinPeriod> findByCabinId(@Param("cabin_id")Long cabinId);

    @Query(value="SELECT * from available_period ap where cabin_id=:cabin_id and ((:start between start_date and end_date)) and ((:end between start_date and end_date))",nativeQuery = true)
    List<AvailableCabinPeriod> cabinIsAvailable(@Param("cabin_id")Long cabinId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query(value="SELECT CASE WHEN  COUNT(ap) > 0 THEN true ELSE false END FROM available_period ap where cabin_id=:cabin_id and ((:start between start_date and end_date) or (:end between start_date and end_date) or (start_date between :start and :end) or (end_date between :start and :end))",nativeQuery = true)
    boolean availablePeriodAlreadyExists(@Param("cabin_id")Long cabinId,@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);

    @Query(value="SELECT * FROM available_period where cabin_id=:cabin_id and start_date=:startDate and end_date=:endDate",nativeQuery = true)
    AvailableCabinPeriod findId(@Param("cabin_id")Long id, @Param("startDate")LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);

    @Query(value="SELECT CASE WHEN  COUNT(ap) > 0 THEN true ELSE false END FROM available_period ap where id=:id and ((:startDate between start_date and end_date) and (:endDate between start_date and end_date))",nativeQuery = true)
    boolean availablePeriodIncludesUnavailable(@Param("id")Long id, @Param("startDate")LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
