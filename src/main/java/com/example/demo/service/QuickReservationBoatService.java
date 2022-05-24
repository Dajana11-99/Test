package com.example.demo.service;


import com.example.demo.dto.QuickReservationBoatDto;
import com.example.demo.model.QuickReservationBoat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface QuickReservationBoatService {
    boolean ownerCreates(QuickReservationBoat quickReservationBoat) throws Exception;
    Set<QuickReservationBoat> getByBoatId(Long cabinId);
    boolean quickReservationExists(Long id, LocalDateTime startDate, LocalDateTime endDate);
    boolean ownerIsNotAvailableQuickResrvation(String ownerUsername, LocalDateTime start, LocalDateTime end);
    Set<QuickReservationBoat> findReservationsByOwnerUsername(String username);
    boolean futureQuickReservationsExist(LocalDateTime currentDate,Long boatId);
    Set<QuickReservationBoat> getPastReservations(String username);
    Integer countReservationsInPeriod(LocalDateTime start, LocalDateTime end, String username);
    double findReservationsAndSumProfit(String username, LocalDateTime start, LocalDateTime end);
    double sumProfitOfPricesCalculatedByHours(List<QuickReservationBoat> reservations, LocalDateTime start, LocalDateTime end);
    QuickReservationBoat getById(Long reservationId);
    void save(QuickReservationBoat reservation);
    Set<QuickReservationBoat> getAvailableReservations();
    boolean makeQuickReservation(QuickReservationBoatDto quickReservationBoatDto);
    boolean boatHasQuickReservationInPeriod(Long boatId, LocalDateTime startDate, LocalDateTime endDate);
    Set<QuickReservationBoat> getUpcomingClientQuickReservations(String clientUsername);
    Set<QuickReservationBoat> getClientQuickReservationsHistory(String clientUsername);
    List<QuickReservationBoat> findAllQucikReservationsForAdminProfit(LocalDateTime start, LocalDateTime end);
    double sumProfitOfPricesCalculatedByHoursForAdmin(List<QuickReservationBoat> reservations, LocalDateTime start, LocalDateTime end);
    boolean checkIfOwnerHasFutureReservations(String username);
    boolean checkIfClientHasFutureReservations(Long userId);
    Integer countReservationsByBoatInPeriod(LocalDateTime start, LocalDateTime end, Long id);
    List<QuickReservationBoat> findReservationsToSumProfit(String id, LocalDateTime localDateTime, LocalDateTime localDateTime1);
    List<QuickReservationBoat> findReservationsToSumProfitByBoat(Long id, LocalDateTime localDateTime, LocalDateTime localDateTime1);
}
