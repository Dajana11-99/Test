package com.example.demo.service;
import com.example.demo.dto.CabinReservationDto;
import com.example.demo.dto.SearchAvailablePeriodsCabinDto;
import com.example.demo.model.Cabin;
import com.example.demo.model.CabinReservation;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ReservationCabinService {

    Set<Cabin> getAvailableCabins(SearchAvailablePeriodsCabinDto searchAvailablePeriodsCabinDto);

    boolean makeReservation(CabinReservationDto cabinReservationDto) throws Exception;

    boolean ownerCreates(CabinReservation cabinReservation, String clientUsername) throws Exception;

    Set<CabinReservation> getPresentByCabinId(Long cabinId);

    boolean reservationExists(Long cabinId, LocalDateTime startDate, LocalDateTime endDate);

    Set<CabinReservation> getUpcomingClientReservationsByUsername(String clientUsername);

    Set<CabinReservation> getClientReservationHistoryByUsername(String clientUsername);

    boolean futureReservationsExist(LocalDateTime currentDate, Long id);

    Set<CabinReservation> findReservationsByOwnerUsername(String ownerUsername);

    Set<CabinReservation> getPastReservations(String ownersUsername);

    CabinReservation getById(Long id);

    boolean checkIfOwnerHasFutureReservations(String username);

    boolean checkIfClientHasFutureReservations(Long userId);


    Integer countReservationsInPeriod(LocalDateTime startWeek, LocalDateTime endWeek, String ownerUsername);

    List<CabinReservation> findReservationsToSumProfit(String ownerUsername, LocalDateTime start, LocalDateTime end);

    double sumProfitOfPricesCalculatedByDays(List<CabinReservation> reservations, LocalDateTime start, LocalDateTime end);

    void save(CabinReservation reservation);

    void markThatReservationIsEvaluated(Long id);

    boolean checkIfReservationIsEvaluated(Long id);

    boolean reservationExists(Long id);

    boolean cabinNotFreeInPeriod(Long cabinId, LocalDateTime startDate, LocalDateTime endDate);

    List<CabinReservation> findAllReservationsForAdminProfit(LocalDateTime start, LocalDateTime end);
    double sumProfitForAdminOfPricesCalculatedByDays(List<CabinReservation> reservations, LocalDateTime start, LocalDateTime end);
    Integer countReservationsInPeriodByCabinId(LocalDateTime start, LocalDateTime end, Long id);

    List<CabinReservation> findReservationsByCabinToSumProfit(Long id, LocalDateTime localDateTime, LocalDateTime localDateTime1);

    Set<Cabin> searchAvailableCabins(SearchAvailablePeriodsCabinDto searchAvailablePeriodsCabinDto);
}
