package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.Penalty;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {

    @Query(value="SELECT CASE WHEN  COUNT(c) >= 3 THEN true ELSE false END FROM penalties c where users_id=:users_id",nativeQuery = true)
    boolean isUserBlockedFromReservation(@Param("users_id")Long usersId);

    @Query(value="SELECT * FROM penalties where users_id=:users_id",nativeQuery = true)
    List<Penalty> getUserPenalties(@Param("users_id")Long usersId);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM penalties where date<:date",nativeQuery = true)
    void deleteOldPenalties(@Param("date")LocalDateTime withDayOfMonth);
}
