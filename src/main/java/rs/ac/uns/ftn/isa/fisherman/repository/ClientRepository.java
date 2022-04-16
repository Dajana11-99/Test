package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.Client;

public interface ClientRepository extends JpaRepository<Client,Integer> {

    @Query(value="SELECT * FROM users where username=:username",nativeQuery = true)
    Client findByUsername(@Param("username")String username);
}
