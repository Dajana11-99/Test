package rs.ac.uns.ftn.isa.fisherman.service;

import org.springframework.cache.annotation.Cacheable;
import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;
import java.util.List;

public interface FishingInstructorService {
    FishingInstructor findByUsername(String fishingInstructorUsername);

    @Cacheable("instructor")
    FishingInstructor findByID(Long id);

    void save(FishingInstructor fishingInstructor);
}
