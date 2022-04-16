package rs.ac.uns.ftn.isa.fisherman.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.mail.MailService;
import rs.ac.uns.ftn.isa.fisherman.mail.QuickActionCabinInfo;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationCabin;
import rs.ac.uns.ftn.isa.fisherman.repository.QuickReservationCabinRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableCabinPeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.CabinSubscriptionService;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationCabinService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationCabinService;

import javax.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class QuickReservationCabinServiceImpl implements QuickReservationCabinService {

    @Autowired
    private AvailableCabinPeriodService availableCabinPeriodService;
    @Autowired
    private ReservationCabinService reservationCabinService;
    @Autowired
    private QuickReservationCabinRepository quickReservationCabinRepository;
    @Autowired
    private CabinSubscriptionService cabinSubscriptionService;
    @Autowired
    private MailService mailService;
    private final Logger logger= LoggerFactory.getLogger(FirebaseServiceImpl.class);
    @Override
    public boolean ownerCreates(QuickReservationCabin quickReservationCabin) {
        if(!validateForReservation(quickReservationCabin)) return false;

        QuickReservationCabin successfullQuickReservation=new QuickReservationCabin(quickReservationCabin.getId(),quickReservationCabin.getStartDate(),
                quickReservationCabin.getEndDate(),null,quickReservationCabin.getPaymentInformation(), quickReservationCabin.isOwnerWroteAReport(),
                quickReservationCabin.getOwnersUsername(),quickReservationCabin.getCabin(),quickReservationCabin.getDiscount(),null);
        successfullQuickReservation.setEvaluated(false);
        quickReservationCabinRepository.save(successfullQuickReservation);
        if(quickReservationCabin.getAddedAdditionalServices()!=null){
            successfullQuickReservation.setAddedAdditionalServices(quickReservationCabin.getAddedAdditionalServices());
            quickReservationCabinRepository.save(successfullQuickReservation);
        }
        //TO DO: poslati mejl onima koji su pretplaceni na akcije od tog cabina
        sendMailNotificationToSubscribedUsers(successfullQuickReservation.getCabin().getId(),successfullQuickReservation.getCabin().getName());
        return true;
    }
    @Override
    public Set<QuickReservationCabin> getAllReports(){
        return  quickReservationCabinRepository.getAllReports();
    }

    @Override
    public Integer countReservationsInPeriod(LocalDateTime startWeek, LocalDateTime endWeek, String ownerUsername) {
        return quickReservationCabinRepository.countReservationsOfThisWeek(startWeek,endWeek,ownerUsername);
    }

    @Override
    public List<QuickReservationCabin> findReservationsToSumProfit(String ownerUsername, LocalDateTime start, LocalDateTime end) {
        return quickReservationCabinRepository.findReservationsInPeriodToSumProfit(ownerUsername,start,end);
    }
    @Override
    public double sumProfitOfPricesCalculatedByDays(List<QuickReservationCabin> reservations, LocalDateTime start, LocalDateTime end){
        double profit=0.0;
        long numOfDaysForReportReservation= 0;
        for(QuickReservationCabin quickReservationCabin: reservations){
            numOfDaysForReportReservation= calculateOverlapingDates(start,end,quickReservationCabin.getStartDate(),quickReservationCabin.getEndDate());
            profit+=(numOfDaysForReportReservation* quickReservationCabin.getPaymentInformation().getOwnersPart())/Duration.between(quickReservationCabin.getStartDate(),quickReservationCabin.getEndDate()).toDays();
        }
        return profit;
    }

    private long calculateOverlapingDates(LocalDateTime startReport, LocalDateTime endReport, LocalDateTime startReservation, LocalDateTime endReservation){
        long numberOfOverlappingDates=0;
        LocalDate start = Collections.max(Arrays.asList(startReport.toLocalDate(), startReservation.toLocalDate()));
        LocalDate end = Collections.min(Arrays.asList(endReport.toLocalDate(), endReservation.toLocalDate()));
        numberOfOverlappingDates = ChronoUnit.DAYS.between(start, end);
        return numberOfOverlappingDates;
    }

    @Override
    public void save(QuickReservationCabin reservation) {
        quickReservationCabinRepository.save(reservation);
    }

    private void sendMailNotificationToSubscribedUsers(Long cabinId,String cabinName){
        Set<String> subscriptionEmails=cabinSubscriptionService.findCabinSubscribers(cabinId);

        for(String email: subscriptionEmails) {
            try {
                String message = cabinName;
                mailService.sendMail(email, message, new QuickActionCabinInfo());
            } catch (MessagingException e) {
                logger.error(e.toString());
            }
        }
    }

    @Override
    public Set<QuickReservationCabin> getByCabinId(Long cabinId) {
        return quickReservationCabinRepository.getByCabinId(cabinId);
    }

    @Override
    public boolean quickReservationExists(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        if(quickReservationCabinRepository.quickReservationExists(id,startDate,endDate).size()>0)
            return true;
        return false;
    }

    @Override
    public boolean futureQuickReservationsExist(LocalDateTime currentDate, Long id) {
        if(quickReservationCabinRepository.futureQuickReservationsExist(currentDate,id).size()>0) return true;
        return false;
    }

    @Override
    public Set<QuickReservationCabin> findReservationsByOwnerId(String ownerUsername) {
        return quickReservationCabinRepository.findReservationsByOwnerUsername(ownerUsername,LocalDateTime.now());
    }

    @Override
    public QuickReservationCabin findReservationById(Long id) {
        return quickReservationCabinRepository.getById(id);
    }


    @Override
    public Set<QuickReservationCabin> getPastReservations(String ownerUsername) {
        return quickReservationCabinRepository.getPastReservations(ownerUsername,LocalDateTime.now());
    }

    @Override
    public void ownerCreatesReview(QuickReservationCabin reservation, boolean successfull) {
        QuickReservationCabin quickReservationCabin=quickReservationCabinRepository.getById(reservation.getId());
        quickReservationCabin.setSuccessfull(successfull);
       //quickReservationCabin.getOwnersReport().setComment(reservation.getOwnersReport().getComment());
       //quickReservationCabin.getOwnersReport().setBadComment(reservation.getOwnersReport().isBadComment());
        quickReservationCabinRepository.save(quickReservationCabin);
    }

    private boolean validateForReservation(QuickReservationCabin cabinQuickReservation){
        if(!availableCabinPeriodService.cabinIsAvailable(cabinQuickReservation.getCabin()
                .getId(),cabinQuickReservation.getStartDate(),cabinQuickReservation.getEndDate())) {
            System.out.println("NEMAM SLOBODAN PERIOD");
            return false;
        }


        if(reservationCabinService.reservationExists(cabinQuickReservation.getCabin()
                .getId(),cabinQuickReservation.getStartDate(),cabinQuickReservation.getEndDate())) {

            System.out.println("imam rezervacije");
            return false;
        }

        if(quickReservationCabinRepository.quickReservationExists(cabinQuickReservation.getCabin()
                .getId(),cabinQuickReservation.getStartDate(),cabinQuickReservation.getEndDate()).size()>0) {
            System.out.println("imam quick rezervacije");
            return false;
        }

        return true;
    }
}
