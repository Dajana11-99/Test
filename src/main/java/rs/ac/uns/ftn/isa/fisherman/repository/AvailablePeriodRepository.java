package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.AvailablePeriod;

public interface AvailablePeriodRepository extends JpaRepository<AvailablePeriod, Long> {
    @Query(value = "select * from available_period where id=:id",nativeQuery = true)
    AvailablePeriod findByPeriodId(@Param("id")Long id);
}
