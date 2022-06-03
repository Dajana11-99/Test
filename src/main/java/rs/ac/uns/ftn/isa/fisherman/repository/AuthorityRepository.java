package rs.ac.uns.ftn.isa.fisherman.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.isa.fisherman.model.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String name);
}