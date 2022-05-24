package com.example.demo.service.impl;

import com.example.demo.model.AdditionalServices;
import com.example.demo.model.Cabin;
import com.example.demo.model.Image;
import com.example.demo.repository.CabinEvaluationRepository;
import com.example.demo.repository.CabinRepository;
import com.example.demo.repository.CabinReservationRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CabinServiceImpl implements CabinService {

    @Autowired
    private CabinReservationRepository cabinReservationRepository;
    @Autowired
    private CabinEvaluationRepository cabinEvaluationRepository;
    @Autowired
    private CabinRepository cabinRepository;
    @Autowired
    private AdditionalServicesService additionalServicesService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ReservationCabinService reservationCabinService;
    @Autowired
    private QuickReservationCabinService quickReservationCabinService;
    public Cabin findById(Long id){
        return cabinRepository.findById(id);
    }
    public List<Cabin> findAll(){
        return cabinRepository.findAll();
    }

    @Override
    public Cabin findByName(String cabin) {
        return cabinRepository.findByName(cabin);
    }

    @Override
    public boolean save(Cabin cabin,Set<AdditionalServices> additionalServices) {
        cabinRepository.save(cabin);
        if (additionalServices!= null) {
            cabin.setAdditionalServices(additionalServices);
            cabinRepository.save(cabin);
        }
        return true;
    }

    @Override
    public void addNewImage(String cabinName, Image image) {
        Cabin cabin= cabinRepository.findByName(cabinName);
        Set<Image> currentImages=cabin.getImages();
        currentImages.add(image);
        cabinRepository.save(cabin);
    }

    @Override
    public Set<Cabin> findByOwnersId(Long id) {
        return cabinRepository.findByOwnersId(id);
    }

    @Override
    public boolean delete(Long id) {
        Cabin cabin=cabinRepository.findById(id);
        if(cabin == null) return  false;
        Set<AdditionalServices> additionalServices=cabin.getAdditionalServices();
        Set<Image> images=cabin.getImages();

            cabinRepository.delete(cabin);
            additionalServicesService.delete(additionalServices);
            imageService.delete(images);

        return true;

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public boolean edit(Cabin newCabin, Boolean deleteOldImages) throws Exception {
        Cabin oldCabin=this.cabinRepository.findByName(newCabin.getName());
        oldCabin.setAddress(newCabin.getAddress());
        oldCabin.setPrice(newCabin.getPrice());
        oldCabin.setNumOfRooms(newCabin.getNumOfRooms());
        oldCabin.setBedsPerRoom(newCabin.getBedsPerRoom());
        oldCabin.setDescription(newCabin.getDescription());
        oldCabin.setRules(newCabin.getRules());
        oldCabin.setCancellingConditions(newCabin.getCancellingConditions());
        Set<AdditionalServices> oldAdditionalServices=oldCabin.getAdditionalServices();
        Set<Image> oldImages= oldCabin.getImages();
        oldCabin.setAdditionalServices(newCabin.getAdditionalServices());
        if(Boolean.TRUE.equals(deleteOldImages))  oldCabin.setImages(new HashSet<>());
            cabinRepository.save(oldCabin);

        Set<AdditionalServices> savedServices= cabinRepository.findByName(oldCabin.getName()).getAdditionalServices();
        if(Boolean.TRUE.equals(deleteOldImages))   imageService.delete(oldImages);
        additionalServicesService.delete(additionalServicesService.findDeletedAdditionalServices(oldAdditionalServices,savedServices));

        return true;
    }

    @Override
    public void updateCabinGrade(Long cabinId){
        Set<Integer> reservations_ids = cabinReservationRepository.getCabinReservationsHistory(cabinId, LocalDateTime.now());
        if(reservations_ids.size()==0)
            return;
        Set<Double> approved_cabin_grades = cabinEvaluationRepository.getAllApprovedCabinEvaluationsByCabinReservationIds(reservations_ids);
        if(approved_cabin_grades.size()==0)
            return;
        double sum = 0;
        for(Double number : approved_cabin_grades)
            sum += number;
        Cabin cabin=this.cabinRepository.findById(cabinId);
        double rating = sum/approved_cabin_grades.size();
        cabin.setRating(rating);
        cabinRepository.save(cabin);
    }

    @Override
    public boolean canBeEditedOrDeleted(Long id) {
        LocalDateTime currentDate=LocalDateTime.now();
        if(reservationCabinService.futureReservationsExist(currentDate,id)) return false;
        if(quickReservationCabinService.futureQuickReservationsExist(currentDate,id)) return false;
        return true;
    }

    @Override
    public Double findAvgCabinRatingByOwnerId(Long ownerId) {
        return cabinRepository.findAvgCabinRatingByOwnerId(ownerId);
    }

}
