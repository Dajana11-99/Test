package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.AdventureReservationCancellation;

import java.time.LocalDateTime;
import java.util.List;

public interface AdventureReservationCancellationRepository extends JpaRepository<AdventureReservationCancellation, Long> {

    @Query(value="SELECT * FROM adventure_reservation_cancellation where users_id=:id",nativeQuery = true)
    List<AdventureReservationCancellation> getByUsersId(@Param("id")Long id);

    @Query(value="SELECT CASE WHEN  COUNT(c) > 0 THEN true ELSE false END FROM adventure_reservation_cancellation c where instructor_id=:instructor_id and users_id=:users_id and (start_date<=:end_date and end_date>=:start_date)",nativeQuery = true)
    boolean clientHasCancellationWithInstructorInPeriod(@Param("instructor_id")Long instructorId, @Param("users_id")Long usersId, @Param("start_date") LocalDateTime startDate, @Param("end_date") LocalDateTime endDate);

}
