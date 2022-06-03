package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.BoatReservationCancellation;

import java.time.LocalDateTime;
import java.util.List;

public interface BoatReservationCancellationRepository extends JpaRepository<BoatReservationCancellation, Long> {

    @Query(value="SELECT * FROM boat_reservation_cancellation where users_id=:id",nativeQuery = true)
    List<BoatReservationCancellation> getByUsersId(@Param("id")Long id);

    @Query(value="SELECT CASE WHEN  COUNT(c) > 0 THEN true ELSE false END FROM boat_reservation_cancellation c where boat_id=:boat_id and users_id=:users_id and (start_date<=:end_date and end_date>=:start_date)",nativeQuery = true)
    boolean clientHasCancellationForBoatInPeriod(@Param("boat_id")Long boatId, @Param("users_id")Long usersId, @Param("start_date") LocalDateTime startDate, @Param("end_date") LocalDateTime endDate);

}
