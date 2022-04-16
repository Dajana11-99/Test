package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.StatisticsReportDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.ReservationPointsMapper;
import rs.ac.uns.ftn.isa.fisherman.model.ReservationPoints;
import rs.ac.uns.ftn.isa.fisherman.service.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/adventureStatisticsReport", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdventureStatisticsReportController {
    @Autowired
    private ReservationPointsService reservationPointsService;
    @Autowired
    private UserService userService;
    @Autowired
    private AdventureReservationService adventureReservationService;
    @Autowired
    private QuickReservationAdventureService quickReservationAdventureService;
    @Autowired
    private DateService dateService;
    private final ReservationPointsMapper reservationPointsMapper=new ReservationPointsMapper();

    @GetMapping("/findAdventureStatistics/{username:.+}/")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<StatisticsReportDto> findAdventureStatistics (@PathVariable("username") String username) {
        StatisticsReportDto adventureStats=new StatisticsReportDto();
        ReservationPoints reservationPoints=reservationPointsService.get();
        adventureStats.setReservationsPointsDto(reservationPointsMapper.reservationsPointsToDto(reservationPoints));
        adventureStats.setOwnerRating(userService.findRatingByUsername(username));
        return new ResponseEntity<>(adventureStats, HttpStatus.OK);
    }
    @GetMapping("/countQuickReservations/{username:.+}/")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<List<Integer>> countQuickReservations (@PathVariable("username") String username) {
        List<Integer> quickReservationCount=new ArrayList<>();
        List<LocalDateTime> thisWeek=dateService.findWeek();
        quickReservationCount.add(quickReservationAdventureService.countReservationsInPeriod(thisWeek.get(0),thisWeek.get(1),username));
        List<LocalDateTime> thisMonth=dateService.findMonth();
        quickReservationCount.add(quickReservationAdventureService.countReservationsInPeriod(thisMonth.get(0),thisMonth.get(1),username));
        List<LocalDateTime> thisYear=dateService.findYear();
        quickReservationCount.add(quickReservationAdventureService.countReservationsInPeriod(thisYear.get(0),thisYear.get(1),username));
        return new ResponseEntity<>(quickReservationCount, HttpStatus.OK);
    }
    @GetMapping("/countReservations/{username:.+}/")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<List<Integer>> countReservations(@PathVariable("username") String username) {
        List<Integer> reservationCount=new ArrayList<>();
        List<LocalDateTime> thisWeek=dateService.findWeek();
        reservationCount.add(adventureReservationService.countReservationsInPeriod(thisWeek.get(0),thisWeek.get(1),username));
        List<LocalDateTime> thisMonth=dateService.findMonth();
        reservationCount.add(adventureReservationService.countReservationsInPeriod(thisMonth.get(0),thisMonth.get(1),username));
        List<LocalDateTime> thisYear=dateService.findYear();
        reservationCount.add(adventureReservationService.countReservationsInPeriod(thisYear.get(0),thisYear.get(1),username));
        return new ResponseEntity<>(reservationCount, HttpStatus.OK);
    }
    @PostMapping("/sumProfit/{username:.+}/")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<List<Double>> sumProfit(@PathVariable("username") String username,@RequestBody List<LocalDateTime> dateRange) {
        List<Double> profit=new ArrayList<>();
        profit.add(adventureReservationService.findReservationsAndSumProfit(username,dateRange.get(0),dateRange.get(1)));
        profit.add(quickReservationAdventureService.findReservationsAndSumProfit(username,dateRange.get(0),dateRange.get(1)));
        return new ResponseEntity<>(profit, HttpStatus.OK);
    }
}
