package com.example.demo.service;
import com.example.demo.model.AdditionalServices;
import com.example.demo.model.Boat;
import com.example.demo.model.Image;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;
import java.util.Set;

public interface BoatService {
    void save(Boat boat);

    boolean addNewBoat(Boat boat, Set<AdditionalServices> services);

    Boat findById(Long id);

    void addNewImage(Boat boat, Image image);

    List<Boat> findByOwnersId(Long id);

    Boat findByName(String name);

    Boat findByNameAndOwner(String boatName, Long boatOwner);

    boolean edit(Boat boat, Boolean deleteOldImages);

    boolean delete(Long id);

    List<Boat> findAll();

    boolean canBeEditedOrDeleted(Long id);

    double findAvgBoatRatingByOwnerId(Long ownerId);
}
