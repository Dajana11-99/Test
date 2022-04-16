package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.isa.fisherman.model.Admin;


public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String username );
}
