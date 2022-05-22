package com.example.demo.controller;

import com.example.demo.dto.ReservationsPointsDto;
import com.example.demo.mapper.ReservationPointsMapper;
import com.example.demo.service.ReservationPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reservationPoints", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationsPointsController {
    @Autowired
    private ReservationPointsService reservationPointsService;
    private ReservationPointsMapper reservationPointsMapper= new ReservationPointsMapper();

    @GetMapping("/get")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReservationsPointsDto> get () {
        ReservationsPointsDto reservationPoints = reservationPointsMapper.reservationsPointsToDto(reservationPointsService.get());
        return new ResponseEntity<>(reservationPoints, HttpStatus.OK);
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> update (@RequestBody ReservationsPointsDto reservationsPointsDto) {
        reservationPointsService.update(reservationPointsMapper.dtoToReservationPoints(reservationsPointsDto));
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
