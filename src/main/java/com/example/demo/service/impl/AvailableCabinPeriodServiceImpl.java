package com.example.demo.service.impl;
import com.example.demo.model.AvailableCabinPeriod;
import com.example.demo.repository.AvailableCabinPeriodRepository;
import com.example.demo.service.AvailableCabinPeriodService;
import com.example.demo.service.AvailablePeriodService;
import com.example.demo.service.QuickReservationCabinService;
import com.example.demo.service.ReservationCabinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@Service
public class AvailableCabinPeriodServiceImpl implements AvailableCabinPeriodService {
    @Autowired
    private AvailableCabinPeriodRepository availableCabinPeriodRepository;

    @Autowired
    private ReservationCabinService reservationCabinService;

    @Autowired
    private QuickReservationCabinService quickReservationCabinService;

    @Autowired
    private AvailablePeriodService availablePeriodService;

    @Override
    public Set<AvailableCabinPeriod> getAvailablePeriod(Long id) {
        return  availableCabinPeriodRepository.findByCabinId(id);
    }

    @Override
    public boolean setAvailableCabinPeriod(AvailableCabinPeriod availableCabinPeriod) {
            if(availableCabinPeriodRepository.availablePeriodAlreadyExists(availableCabinPeriod.getCabin().getId(),
                    availableCabinPeriod.getStartDate(),availableCabinPeriod.getEndDate())) return false;
            availableCabinPeriodRepository.save(availableCabinPeriod);
            return true;
    }

    @Override
    public List<AvailableCabinPeriod> findAll() {
        return availableCabinPeriodRepository.findAll();
    }

    @Override
    public boolean cabinIsAvailable(Long cabinId, LocalDateTime start, LocalDateTime end) {
        if(availableCabinPeriodRepository.cabinIsAvailable(cabinId,start,end).size()>0)
            return true;
        return false;
    }

    @Override
    public boolean deleteAvailableCabinsPeriod(AvailableCabinPeriod availablePeriod) {
        AvailableCabinPeriod availableCabinPeriodToDelete= availableCabinPeriodRepository.findId(availablePeriod.getCabin().getId(),
                availablePeriod.getStartDate(),availablePeriod.getEndDate());
        if(availableCabinPeriodToDelete == null) return false;

        if(!reservationsDontExistInPeriod(availableCabinPeriodToDelete)) return false;

        availablePeriodService.deleteAvailablePeriod(availableCabinPeriodToDelete.getId());
        return true;
    }

    @Override
    public boolean editAvailableCabinsPeriod(AvailableCabinPeriod oldPeriod, AvailableCabinPeriod newPeriod) {
        AvailableCabinPeriod availableCabinPeriodToEdit = availableCabinPeriodRepository.findId(oldPeriod.getCabin().getId(),
                oldPeriod.getStartDate(), oldPeriod.getEndDate());
        if (availableCabinPeriodToEdit == null) return false;
        if (!reservationsDontExistInPeriod(newPeriod)) return false;
        if (!availableCabinPeriodRepository.availablePeriodIncludesUnavailable(availableCabinPeriodToEdit.getId(),
                newPeriod.getStartDate(), newPeriod.getEndDate())) {
            return false;
        }
        setEditedAvailablePeriod(availableCabinPeriodToEdit, newPeriod);
       /* LocalDateTime startOld=oldPeriod.getStartDate();
        LocalDateTime endOld=oldPeriod.getEndDate();

        LocalDateTime startNew=newPeriod.getStartDate();
        LocalDateTime endNew=newPeriod.getEndDate();

        if(startOld.equals(startNew)){
            availableCabinPeriodToEdit.setStartDate(endNew.plusMinutes(1));
        }else if(endOld.equals(endNew)){
            availableCabinPeriodToEdit.setEndDate(startNew.minusMinutes(1));
        }else {
            availableCabinPeriodToEdit.setEndDate(startNew.minusMinutes(1));
            newPeriod.setEndDate(endOld);
            newPeriod.setStartDate(endNew.plusMinutes(1));
            availableCabinPeriodRepository.save(newPeriod);
        }*/
        //availableCabinPeriodRepository.save(edited);
        return true;
    }

    public void setEditedAvailablePeriod(AvailableCabinPeriod oldPeriod, AvailableCabinPeriod newPeriod){
        LocalDateTime startOld=oldPeriod.getStartDate();
        LocalDateTime endOld=oldPeriod.getEndDate();

        LocalDateTime startNew=newPeriod.getStartDate();
        LocalDateTime endNew=newPeriod.getEndDate();

        if(startOld.equals(startNew)){
            oldPeriod.setStartDate(endNew.plusMinutes(1));
        }else if(endOld.equals(endNew)){
            oldPeriod.setEndDate(startNew.minusMinutes(1));
        }else {
            oldPeriod.setEndDate(startNew.minusMinutes(1));
            newPeriod.setEndDate(endOld);
            newPeriod.setStartDate(endNew.plusMinutes(1));
            availableCabinPeriodRepository.save(newPeriod);
        }
        availableCabinPeriodRepository.save(oldPeriod);
    }

    private boolean reservationsDontExistInPeriod(AvailableCabinPeriod availablePeriod){
        if(reservationCabinService.reservationExists(availablePeriod.getCabin().getId()
                ,availablePeriod.getStartDate(),availablePeriod.getEndDate())) return false;


        if(quickReservationCabinService.quickReservationExists(availablePeriod.getCabin().getId(),
                availablePeriod.getStartDate(),availablePeriod.getEndDate())) return false;

        return true;
    }

}
