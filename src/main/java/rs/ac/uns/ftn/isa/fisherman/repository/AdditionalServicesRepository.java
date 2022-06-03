package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.isa.fisherman.model.AdditionalServices;

public interface AdditionalServicesRepository extends JpaRepository<AdditionalServices,Integer> {
    AdditionalServices findById(Long id);
}
