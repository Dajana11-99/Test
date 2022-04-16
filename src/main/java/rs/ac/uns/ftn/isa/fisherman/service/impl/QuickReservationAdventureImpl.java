package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.AdventureReservation;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationAdventure;
import rs.ac.uns.ftn.isa.fisherman.repository.QuickReservationAdventureRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AdventureReservationService;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableInstructorPeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationAdventureService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class QuickReservationAdventureImpl implements QuickReservationAdventureService {
    @Autowired
    private QuickReservationAdventureRepository quickReservationAdventureRepository;
    @Autowired
    private AvailableInstructorPeriodService availableInstructorPeriodService;
    @Autowired
    private AdventureReservationService adventureReservationService;
    @Override
    public boolean instructorCreates(QuickReservationAdventure quickReservationAdventure) {
        if(!validateForReservation(quickReservationAdventure)) return false;

        QuickReservationAdventure successfullQuickReservation=new QuickReservationAdventure(quickReservationAdventure.getId(),quickReservationAdventure.getStartDate(),
                quickReservationAdventure.getEndDate(),null,quickReservationAdventure.getPaymentInformation(),quickReservationAdventure.isOwnerWroteAReport(),
                quickReservationAdventure.getOwnersUsername(),
                quickReservationAdventure.getAdventure(),quickReservationAdventure.getFishingInstructor(),quickReservationAdventure.getDiscount(),null);
        successfullQuickReservation.setEvaluated(false);
        quickReservationAdventureRepository.save(successfullQuickReservation);
        if(quickReservationAdventure.getAddedAdditionalServices()!=null){
            successfullQuickReservation.setAddedAdditionalServices(quickReservationAdventure.getAddedAdditionalServices());
            quickReservationAdventureRepository.save(successfullQuickReservation);
        }
        //TO DO: poslati mejl onima koji su pretplaceni na akcije od tog cabina

        return true;
    }

    @Override
    public Set<QuickReservationAdventure> getByInstructorUsername(String username) {
        return  quickReservationAdventureRepository.getByInstructorUsername(username,LocalDateTime.now());
    }

    @Override
    public boolean quickReservationExists(String username, LocalDateTime startDate, LocalDateTime endDate) {
        return quickReservationAdventureRepository.quickReservationExists(username,startDate,endDate);
    }

    @Override
    public boolean futureQuickReservationsExist(LocalDateTime currentDate,Long id) {
        return quickReservationAdventureRepository.futureQuickReservationsExist(currentDate,id);
    }

    @Override
    public Integer countReservationsInPeriod(LocalDateTime start, LocalDateTime end, String username) {
        return quickReservationAdventureRepository.countReservationsInPeriod(start,end,username);
    }


    @Override
    public void save(QuickReservationAdventure quickReservationAdventure) {
        quickReservationAdventureRepository.save(quickReservationAdventure);
    }

    @Override
    public QuickReservationAdventure findById(Long id) {
        return quickReservationAdventureRepository.getById(id);
    }


    @Override
    public Set<QuickReservationAdventure> getPastReservations(String username) {
        return quickReservationAdventureRepository.getPastReservations(username,LocalDateTime.now());
    }


    private boolean validateForReservation(QuickReservationAdventure quickReservationAdventure){
        if(!availableInstructorPeriodService.instructorIsAvailable(quickReservationAdventure.getFishingInstructor()
                .getId(),quickReservationAdventure.getStartDate(),quickReservationAdventure.getEndDate())) return false;

        if(adventureReservationService.reservationExists(quickReservationAdventure.getOwnersUsername(),quickReservationAdventure.getStartDate(),quickReservationAdventure.getEndDate())) return false;

        if(quickReservationAdventureRepository.quickReservationExists(quickReservationAdventure.getOwnersUsername()
                ,quickReservationAdventure.getStartDate(),quickReservationAdventure.getEndDate())) return false;

        return true;
    }
    public double findReservationsAndSumProfit(String ownerUsername, LocalDateTime start, LocalDateTime end) {
        return sumProfitOfPricesCalucatedByHours(quickReservationAdventureRepository.findReservationsInPeriodToSumProfit(ownerUsername,start,end),start,end);
    }
    @Override
    public double sumProfitOfPricesCalucatedByHours(List<QuickReservationAdventure> reservations, LocalDateTime start, LocalDateTime end){
        double profit=0.0;
        double numOfHoursForReportReservation= 0.0;
        double reservationHours=0.0;
        for(QuickReservationAdventure adventureReservation: reservations){
            numOfHoursForReportReservation= calculateOverlapingDates(start,end,adventureReservation.getStartDate(),adventureReservation.getEndDate());
            reservationHours= Duration.between(adventureReservation.getStartDate(),adventureReservation.getEndDate()).toMinutes()/60d;
            profit+=(numOfHoursForReportReservation* adventureReservation.getPaymentInformation().getOwnersPart())/reservationHours;
        }
        return profit;
    }

    private double calculateOverlapingDates(LocalDateTime startReport, LocalDateTime endReport, LocalDateTime startReservation, LocalDateTime endReservation){
        double numberOfOverlappingHours=0;
        LocalDateTime start = Collections.max(Arrays.asList(startReport, startReservation));
        LocalDateTime end = Collections.min(Arrays.asList(endReport, endReservation));
        numberOfOverlappingHours = ChronoUnit.MINUTES.between(start, end);

        System.out.println("Minuta izmedju  "+numberOfOverlappingHours);
        System.out.println("Sati izmedju  "+numberOfOverlappingHours/60d);
        return numberOfOverlappingHours/60d;
    }
}
