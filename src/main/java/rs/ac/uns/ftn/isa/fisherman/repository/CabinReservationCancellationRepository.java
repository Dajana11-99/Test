package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservationCancellation;

import java.time.LocalDateTime;
import java.util.List;

public interface CabinReservationCancellationRepository extends JpaRepository<CabinReservationCancellation, Long> {

    @Query(value="SELECT * FROM cabin_reservation_cancellation where users_id=:id",nativeQuery = true)
    List<CabinReservationCancellation> getByUsersId(@Param("id")Long id);

    @Query(value="SELECT CASE WHEN  COUNT(c) > 0 THEN true ELSE false END FROM cabin_reservation_cancellation c where cabin_id=:cabin_id and users_id=:users_id and (start_date<=:end_date and end_date>=:start_date)",nativeQuery = true)
    boolean clientHasCancellationForCabinInPeriod(@Param("cabin_id")Long cabinId,@Param("users_id")Long usersId, @Param("start_date") LocalDateTime startDate, @Param("end_date") LocalDateTime endDate);
}
