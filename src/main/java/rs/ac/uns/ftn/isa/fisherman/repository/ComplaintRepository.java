package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.isa.fisherman.model.Complaint;

public interface ComplaintRepository  extends JpaRepository<Complaint, Long> {

}
