package rs.ac.uns.ftn.isa.fisherman.service;

import org.springframework.cache.annotation.Cacheable;
import rs.ac.uns.ftn.isa.fisherman.model.AdditionalServices;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.model.Image;

import java.util.List;
import java.util.Set;

public interface CabinService {
    Cabin findById(Long id);
    List<Cabin> findAll();
    @Cacheable("cabin")
    Cabin findByName(String cabin);
    boolean save(Cabin cabin, Set<AdditionalServices> additionalServices);
    void addNewImage(String cabinName, Image image);
    Set<Cabin> findByOwnersId(Long id);
    void delete(Long id) throws Exception;
    boolean edit(Cabin cabin, Boolean deleteOldImages) throws Exception;
    boolean canBeEditedOrDeleted(Long id);
    Double findAvgCabinRatingByOwnerId(Long ownerId);
    void updateCabinGrade(Long cabinId);
}
