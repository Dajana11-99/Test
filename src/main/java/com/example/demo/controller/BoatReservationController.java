package com.example.demo.controller;
import com.example.demo.dto.*;
import com.example.demo.mapper.BoatMapper;
import com.example.demo.mapper.BoatReservationMapper;
import com.example.demo.model.Boat;
import com.example.demo.model.BoatOwner;
import com.example.demo.model.BoatOwnersReservationReport;
import com.example.demo.model.BoatReservation;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/reservationBoat", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoatReservationController {
    @Autowired
    private BoatReservationService boatReservationService;
    @Autowired
    private BoatOwnerService boatOwnerService;
    @Autowired
    private BoatOwnersReservationReportService boatOwnersReservationReportService;
    @Autowired
    private PenaltyService penaltyService;
    @Autowired
    private BoatReservationCancellationService boatReservationCancellationService;

    private final BoatMapper boatMapper = new BoatMapper();
    private final BoatReservationMapper boatReservationMapper=new BoatReservationMapper();
    @PostMapping("/ownerCreates/{username:.+}/")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<String> ownerCreates (@PathVariable("username") String username,@RequestBody BoatReservationDto boatReservationDto) {
        BoatReservation boatReservation= boatReservationMapper.boatReservationOwnerDtoToBoatReservation(boatReservationDto);
        BoatOwner boatOwner= boatOwnerService.findByUsername(username);
        boatReservation.getBoat().setBoatOwner(boatOwner);
        if(boatReservationService.ownerCreates(boatReservation,boatReservationDto.getClientUsername())) {
            return new ResponseEntity<>("Success.", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Unsuccessfull reservation.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value= "/getByBoatId/{boatId}")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<Set<BoatReservationDto>> getByBoatId(@PathVariable ("boatId") Long boatId) {
        Set<BoatReservationDto> boatReservationDtos= new HashSet<>();
        for(BoatReservation boatReservation: boatReservationService.getPresentByCabinId(boatId))
            boatReservationDtos.add(boatReservationMapper.boatReservationToBoatReservationDto(boatReservation));
        return new ResponseEntity<>(boatReservationDtos,HttpStatus.OK);
    }

    @GetMapping("/getByOwnerUsername/{username:.+}/")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<Set<BoatReservationDto>> getByOwnerUsername (@PathVariable("username") String username) {
        Set<BoatReservationDto> boatReservationDtos=new HashSet<>();
        for(BoatReservation boatReservation: boatReservationService.findReservationsByOwnerUsername(username)){
            boatReservationDtos.add(boatReservationMapper.boatReservationToBoatReservationDto(boatReservation));
        }
        return new ResponseEntity<>(boatReservationDtos,HttpStatus.OK);
    }

    @GetMapping("/getPastReservations/{username:.+}/")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<List<BoatReservationDto>> getPastReservations (@PathVariable("username") String username) {
        List<BoatReservationDto> boatReservationDtos=new ArrayList<>();
        for(BoatReservation boatReservation: boatReservationService.getPastReservations(username)){
            boatReservationDtos.add(boatReservationMapper.boatReservationToBoatReservationDto(boatReservation));
        }
        return new ResponseEntity<>(boatReservationDtos,HttpStatus.OK);
    }
    @PostMapping("/ownerCreatesReview/{reservationId}")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<String> writeAReview (@PathVariable("reservationId") Long reservationId, @RequestBody OwnersReportDto ownersReportDto) {
        BoatReservation reservation=boatReservationService.getById(reservationId);
        BoatOwnersReservationReport reservationReport=new BoatOwnersReservationReport(ownersReportDto.getId(),
                ownersReportDto.isBadComment(),ownersReportDto.getComment(),ownersReportDto.getOwnersUsername(),
                ownersReportDto.getClientUsername(),ownersReportDto.isApproved(),reservation);
        boatOwnersReservationReportService.save(reservationReport);
        reservation.setSuccessfull(ownersReportDto.isSuccess());
        reservation.setOwnerWroteAReport(true);
        boatReservationService.save(reservation);
        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }

    @PostMapping("/getAvailableBoats")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Set<BoatDto>> getAvailableBoats (@RequestBody SearchAvailablePeriodsBoatAndAdventureDto searchAvailablePeriodsBoatDto) {
        Set<BoatDto> boatsDto= new HashSet<>();
        for(Boat boat:boatReservationService.getAvailableBoats(searchAvailablePeriodsBoatDto)){
            boatsDto.add(boatMapper.boatToBoatDto(boat));
        }
        return new ResponseEntity<>(boatsDto, HttpStatus.OK);
    }

    @PostMapping("/makeReservation")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> makeReservation (@RequestBody BoatReservationDto boatReservationDto) {
        if(penaltyService.isUserBlockedFromReservation(boatReservationDto.getClientUsername()))
            return new ResponseEntity<>("Client banned from making reservations!", HttpStatus.BAD_REQUEST);
        if(boatReservationCancellationService.clientHasCancellationForBoatInPeriod(boatReservationDto))
            return new ResponseEntity<>("Client has cancellation for boat in given period!", HttpStatus.BAD_REQUEST);
        if(boatReservationService.makeReservation(boatReservationDto))
            return new ResponseEntity<>("Success.", HttpStatus.OK);
        else
            return new ResponseEntity<>("Boat already reserved in period!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value= "/getUpcomingReservations")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Set<BoatReservationDto>> getUpcomingReservations(@RequestBody UserRequestDTO userRequestDTO) {
        Set<BoatReservationDto> boatReservationDtos= new HashSet<>();
        for(BoatReservation boatReservation: boatReservationService.getUpcomingClientReservationsByUsername(userRequestDTO.getUsername()))
            boatReservationDtos.add(boatReservationMapper.boatReservationToBoatReservationDto(boatReservation));
        return new ResponseEntity<>(boatReservationDtos,HttpStatus.OK);
    }

    @PostMapping(value= "/getReservationsHistory")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Set<BoatReservationDto>> getReservationsHistory(@RequestBody UserRequestDTO userRequestDTO) {
        Set<BoatReservationDto> boatReservationDtos= new HashSet<>();
        for(BoatReservation boatReservation: boatReservationService.getClientReservationHistoryByUsername(userRequestDTO.getUsername())){
            boatReservationDtos.add(boatReservationMapper.boatReservationToBoatReservationDto(boatReservation));
        }
        return new ResponseEntity<>(boatReservationDtos,HttpStatus.OK);
    }

    @PostMapping("/cancelReservation")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> cancelReservation (@RequestBody BoatReservationDto boatReservationDto) {
        if(boatReservationDto.getStartDate().minusDays(3).isBefore(LocalDateTime.now()))
            return new ResponseEntity<>("Unsuccessful cancellation. Less than 3 days left until start!", HttpStatus.BAD_REQUEST);
        if(!boatReservationService.reservationExists(boatReservationDto.getId()))
            return new ResponseEntity<>("Unsuccessful cancellation. Reservation doesn't exist or it is already cancelled!", HttpStatus.BAD_REQUEST);
        if(boatReservationCancellationService.addCancellation(boatReservationDto))
            return new ResponseEntity<>("Successful cancellation.", HttpStatus.OK);
        else
            return new ResponseEntity<>("Unsuccessful cancellation.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/searchAvailableBoats")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Set<BoatDto>> searchAvailableBoats (@RequestBody SearchAvailablePeriodsBoatAndAdventureDto searchAvailablePeriodsBoatDto) {
        Set<BoatDto> boatsDto= new HashSet<>();
        for(Boat boat:boatReservationService.searchAvailableBoats(searchAvailablePeriodsBoatDto)){
            boatsDto.add(boatMapper.boatToBoatDto(boat));
        }
        return new ResponseEntity<>(boatsDto, HttpStatus.OK);
    }
}
