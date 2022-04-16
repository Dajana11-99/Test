package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.isa.fisherman.model.CabinComplaint;

public interface CabinComplaintRepository extends JpaRepository<CabinComplaint, Long> {
}
