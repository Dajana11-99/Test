package rs.ac.uns.ftn.isa.fisherman.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.AdventureReservation;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationBoat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface QuickReservationBoatRepository extends JpaRepository<QuickReservationBoat,Long> {
    @Query(value="SELECT * FROM quick_reservation_boat where boat_id=:boat_id and ((:startDate between start_date and end_date) or (:endDate  between start_date and end_date) or (start_date  between :startDate and :endDate) or (end_date  between :startDate and :endDate)) ",nativeQuery = true)
    List<QuickReservationBoat> quickReservationExists(@Param("boat_id")Long boatId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(value="SELECT * FROM quick_reservation_boat where boat_id=:boat_id ",nativeQuery = true)
    Set<QuickReservationBoat> getByBoatId(@Param("boat_id")Long boatId);

    @Query(value="SELECT CASE WHEN  COUNT(res) > 0 THEN true ELSE false END FROM quick_reservation_boat res where res.owners_username=:ownersUsername and res.needs_captain_services=true and ((:startDate between start_date and end_date) or (:endDate  between start_date and end_date) or (start_date  between :startDate and :endDate) or (end_date  between :startDate and :endDate)) ",nativeQuery = true)
    boolean ownerIsNotAvailable(@Param("ownersUsername") String ownersUsername, @Param("startDate") LocalDateTime start, @Param("endDate") LocalDateTime end);

    @Query(value="SELECT * FROM quick_reservation_boat res where res.owners_username=:ownersUsername and (:currentDate <= end_date)",nativeQuery = true)
    Set<QuickReservationBoat> findReservationsByOwnerId(@Param("ownersUsername")String ownersUsername, @Param("currentDate")LocalDateTime currentDate);

    @Query(value="SELECT CASE WHEN  COUNT(c) > 0 THEN true ELSE false END FROM quick_reservation_boat c where boat_id=:boat_id and (:currentDate <= end_date) ",nativeQuery = true)
    boolean futureQuickReservationsExist(@Param("currentDate")LocalDateTime currentDate,@Param("boat_id") Long boatId);

    @Query(value="SELECT * FROM quick_reservation_boat res where res.owners_username=:ownersUsername  and (:currentDate > end_date) ",nativeQuery = true)
    Set<QuickReservationBoat> getPastReservations(@Param("ownersUsername")String ownersUsername, @Param("currentDate")LocalDateTime currentDate);

    @Query(value="SELECT * FROM quick_reservation_boat where id=:id",nativeQuery = true)
    QuickReservationBoat getById(@Param("id")Long id);

    @Query(value="select count(cr.id) from quick_reservation_boat cr where cr.owners_username=:ownersUsername and ((cr.start_date between :start and :end) or (cr.end_date between :start and :end))",nativeQuery = true)
    Integer countReservationsInPeriod(@Param("start")LocalDateTime startWeek, @Param("end") LocalDateTime endWeek, @Param("ownersUsername") String ownersUsername);

    @Query(value="select * from quick_reservation_boat cr where cr.owners_username=:ownersUsername and ((cr.start_date between :start and :end) or (cr.end_date between :start and :end) or ((:start between cr.start_date and cr.end_date) and (:end between cr.start_date and cr.end_date)))",nativeQuery = true)
    List<QuickReservationBoat> findReservationsInPeriodToSumProfit(@Param("ownersUsername")String ownersUsername , @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
