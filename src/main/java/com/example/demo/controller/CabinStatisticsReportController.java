package com.example.demo.controller;
import com.example.demo.dto.StatisticsReportDto;
import com.example.demo.mapper.ReservationPointsMapper;
import com.example.demo.model.ReservationPoints;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/cabinStatisticsReport", produces = MediaType.APPLICATION_JSON_VALUE)
public class CabinStatisticsReportController {
    @Autowired
    private ReservationPointsService reservationPointsService;
    @Autowired
    private UserService userService;
    @Autowired
    private CabinService cabinService;
    @Autowired
    private ReservationCabinService reservationCabinService;
    @Autowired
    private QuickReservationCabinService quickReservationCabinService;
    @Autowired
    private DateService dateService;
    private final ReservationPointsMapper reservationPointsMapper=new ReservationPointsMapper();

    @GetMapping("/findCabinStatistics/{username:.+}/")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<StatisticsReportDto> findCabinStatistics (@PathVariable("username") String username) {
        StatisticsReportDto cabinStats=new StatisticsReportDto();
        ReservationPoints reservationPoints=reservationPointsService.get();
        cabinStats.setReservationsPointsDto(reservationPointsMapper.reservationsPointsToDto(reservationPoints));
        cabinStats.setOwnerRating(userService.findRatingByUsername(username));
        Long ownerId=  userService.findByUsername(username).getId();
        cabinStats.setAvgRating(cabinService.findAvgCabinRatingByOwnerId(ownerId));
        return new ResponseEntity<>(cabinStats, HttpStatus.OK);
    }
    @GetMapping("/countQuickReservations/{username:.+}/")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<List<Integer>> countQuickReservations (@PathVariable("username") String username) {
        List<Integer> quickReservationCount=new ArrayList<>();
        List<LocalDateTime> thisWeek=dateService.findWeek();
        quickReservationCount.add(quickReservationCabinService.countReservationsInPeriod(thisWeek.get(0),thisWeek.get(1),username));
        List<LocalDateTime> thisMonth=dateService.findMonth();
        quickReservationCount.add(quickReservationCabinService.countReservationsInPeriod(thisMonth.get(0),thisMonth.get(1),username));
        List<LocalDateTime> thisYear=dateService.findYear();
        quickReservationCount.add(quickReservationCabinService.countReservationsInPeriod(thisYear.get(0),thisYear.get(1),username));
        return new ResponseEntity<>(quickReservationCount, HttpStatus.OK);
    }
    @GetMapping("/countReservations/{username:.+}/")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<List<Integer>> countReservations(@PathVariable("username") String username) {
        List<Integer> reservationCount=new ArrayList<>();
        List<LocalDateTime> thisWeek=dateService.findWeek();
        reservationCount.add(reservationCabinService.countReservationsInPeriod(thisWeek.get(0),thisWeek.get(1),username));
        List<LocalDateTime> thisMonth=dateService.findMonth();
        reservationCount.add(reservationCabinService.countReservationsInPeriod(thisMonth.get(0),thisMonth.get(1),username));
        List<LocalDateTime> thisYear=dateService.findYear();
        reservationCount.add(reservationCabinService.countReservationsInPeriod(thisYear.get(0),thisYear.get(1),username));
        return new ResponseEntity<>(reservationCount, HttpStatus.OK);
    }
   ///////////////////////////////////
    @GetMapping("/countQuickReservationsByCabin/{cabinName}")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<List<Integer>> countQuickReservationsById (@PathVariable("cabinName") String cabinName) {
        List<Integer> quickReservationCount=new ArrayList<>();
        Long id=cabinService.findByName(cabinName).getId();
        List<LocalDateTime> thisWeek=dateService.findWeek();
        quickReservationCount.add(quickReservationCabinService.countReservationsInPeriodByCabinId(thisWeek.get(0),thisWeek.get(1),id));
        List<LocalDateTime> thisMonth=dateService.findMonth();
        quickReservationCount.add(quickReservationCabinService.countReservationsInPeriodByCabinId(thisMonth.get(0),thisMonth.get(1),id));
        List<LocalDateTime> thisYear=dateService.findYear();
        quickReservationCount.add(quickReservationCabinService.countReservationsInPeriodByCabinId(thisYear.get(0),thisYear.get(1),id));

        return new ResponseEntity<>(quickReservationCount, HttpStatus.OK);
    }
    @GetMapping("/countReservationsByCabin/{cabinName}")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<List<Integer>> countReservationsById(@PathVariable("cabinName") String cabinName) {
        List<Integer> reservationCount=new ArrayList<>();
        List<LocalDateTime> thisWeek=dateService.findWeek();
        Long id=cabinService.findByName(cabinName).getId();
        reservationCount.add(reservationCabinService.countReservationsInPeriodByCabinId(thisWeek.get(0),thisWeek.get(1),id));
        List<LocalDateTime> thisMonth=dateService.findMonth();
        reservationCount.add(reservationCabinService.countReservationsInPeriodByCabinId(thisMonth.get(0),thisMonth.get(1),id));
        List<LocalDateTime> thisYear=dateService.findYear();
        reservationCount.add(reservationCabinService.countReservationsInPeriodByCabinId(thisYear.get(0),thisYear.get(1),id));
        return new ResponseEntity<>(reservationCount, HttpStatus.OK);
    }
    @PostMapping("/sumProfit/{username:.+}/")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<List<Double>> sumProfit(@PathVariable("username") String username,@RequestBody List<LocalDateTime> dateRange) {
        List<Double> profit=new ArrayList<>();
        profit.add(reservationCabinService.sumProfitOfPricesCalculatedByDays(
                reservationCabinService.findReservationsToSumProfit(username,dateRange.get(0),dateRange.get(1)),dateRange.get(0),dateRange.get(1)
                ));
        profit.add(
                quickReservationCabinService.sumProfitOfPricesCalculatedByDays(
                        quickReservationCabinService.findReservationsToSumProfit(username,dateRange.get(0),dateRange.get(1)),
                        dateRange.get(0),dateRange.get(1)
                )

        );
        return new ResponseEntity<>(profit, HttpStatus.OK);
    }
    @PostMapping("/sumProfitByCabin/{cabinName}")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<List<Double>> sumProfitByCabin(@PathVariable("cabinName") String cabinName,@RequestBody List<LocalDateTime> dateRange) {
        List<Double> profit=new ArrayList<>();
        Long id=cabinService.findByName(cabinName).getId();
        profit.add(reservationCabinService.sumProfitOfPricesCalculatedByDays(
                reservationCabinService.findReservationsByCabinToSumProfit(id,dateRange.get(0),dateRange.get(1)),dateRange.get(0),dateRange.get(1)
        ));
        profit.add(
                quickReservationCabinService.sumProfitOfPricesCalculatedByDays(
                        quickReservationCabinService.findReservationsByCabinToSumProfit(id,dateRange.get(0),dateRange.get(1)),
                        dateRange.get(0),dateRange.get(1)
                )

        );
        return new ResponseEntity<>(profit, HttpStatus.OK);
    }


    @GetMapping("/sumWeekProfitOfAllCabinsForAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Double> sumWeekProfit() {
        double profit=0.0;
        List<LocalDateTime> thisWeek=dateService.findWeek();
        profit+=reservationCabinService.sumProfitForAdminOfPricesCalculatedByDays(
                reservationCabinService.findAllReservationsForAdminProfit(thisWeek.get(0),thisWeek.get(1)),thisWeek.get(0),thisWeek.get(1)
        );
        profit+=quickReservationCabinService.sumProfitOfPricesCalculatedByDaysForAdmin(
                quickReservationCabinService.findAllQucikReservationsForAdminProfit(thisWeek.get(0),thisWeek.get(1)),
                thisWeek.get(0),thisWeek.get(1)
        );
        return new ResponseEntity<>(profit, HttpStatus.OK);
    }
    @GetMapping("/sumTodaysProfitOfAllCabinsForAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Double> sumTodaysProfit() {
        double profit=0.0;
        List<LocalDateTime> today=new ArrayList<>();
        LocalDate date= LocalDate.now();
        LocalDateTime start= LocalTime.MIN.atDate(date);
        LocalDateTime end= LocalTime.MAX.atDate(date);

        profit+=reservationCabinService.sumProfitForAdminOfPricesCalculatedByDays(
                reservationCabinService.findAllReservationsForAdminProfit(start,end),start,end
        );
        profit+=quickReservationCabinService.sumProfitOfPricesCalculatedByDaysForAdmin(
                quickReservationCabinService.findAllQucikReservationsForAdminProfit(start,end),
                start,end
        );
        return new ResponseEntity<>(profit, HttpStatus.OK);
    }
    @GetMapping("/sumMonthProfitOfAllCabinsForAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Double> sumMothProfit() {
        double profit=0.0;
        List<LocalDateTime> thisMonth=dateService.findMonth();
        profit+=reservationCabinService.sumProfitForAdminOfPricesCalculatedByDays(
                reservationCabinService.findAllReservationsForAdminProfit(thisMonth.get(0),thisMonth.get(1)),thisMonth.get(0),thisMonth.get(1)
        );
        profit+=quickReservationCabinService.sumProfitOfPricesCalculatedByDaysForAdmin(
                quickReservationCabinService.findAllQucikReservationsForAdminProfit(thisMonth.get(0),thisMonth.get(1)),
                thisMonth.get(0),thisMonth.get(1)
        );
        return new ResponseEntity<>(profit, HttpStatus.OK);
    }
    @GetMapping("/sumYearProfitOfAllCabinsForAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Double> sumYearProfit() {
        double profit=0.0;
        List<LocalDateTime> thisYear=dateService.findYear();
        profit+=reservationCabinService.sumProfitForAdminOfPricesCalculatedByDays(
                reservationCabinService.findAllReservationsForAdminProfit(thisYear.get(0),thisYear.get(1)),thisYear.get(0),thisYear.get(1)
        );
        profit+=quickReservationCabinService.sumProfitOfPricesCalculatedByDaysForAdmin(
                quickReservationCabinService.findAllQucikReservationsForAdminProfit(thisYear.get(0),thisYear.get(1)),
                thisYear.get(0),thisYear.get(1)
        );
        return new ResponseEntity<>(profit, HttpStatus.OK);
    }
}
