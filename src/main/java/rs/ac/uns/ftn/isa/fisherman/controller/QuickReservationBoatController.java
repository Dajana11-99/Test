package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.OwnersReportDto;
import rs.ac.uns.ftn.isa.fisherman.dto.QuickReservationBoatDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.QuickReservationBoatMapper;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.service.BoatOwnerService;
import rs.ac.uns.ftn.isa.fisherman.service.BoatOwnersQuickReservationReportService;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationBoatService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/quickReservationBoat", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuickReservationBoatController {
    @Autowired
    private QuickReservationBoatService quickReservationBoatService;
    @Autowired
    private BoatOwnerService boatOwnerService;
    @Autowired
    private BoatOwnersQuickReservationReportService boatOwnersQuickReservationReportService;
    private final QuickReservationBoatMapper quickReservationBoatMapper= new QuickReservationBoatMapper();

    @PostMapping("/ownerCreates/{username:.+}/")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<String> ownerCreates (@PathVariable("username") String username,@RequestBody QuickReservationBoatDto quickReservationBoatDto) {
        QuickReservationBoat quickReservationBoat= quickReservationBoatMapper.dtoToBoatQuickReservation(quickReservationBoatDto);
        BoatOwner boatOwner= boatOwnerService.findByUsername(username);
        quickReservationBoat.getBoat().setBoatOwner(boatOwner);
        if(quickReservationBoatService.ownerCreates(quickReservationBoat)){
            return new ResponseEntity<>("Success.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Unsuccessfull reservation.", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value= "/getByBoatId/{boatId}")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<Set<QuickReservationBoatDto>> getPresentByBoatId(@PathVariable ("boatId") Long boatId) {
        Set<QuickReservationBoatDto> boatReservationDtos= new HashSet<>();
        for(QuickReservationBoat quickReservationBoat: quickReservationBoatService.getByBoatId(boatId))
            boatReservationDtos.add(quickReservationBoatMapper.boatQuickReservationToDto(quickReservationBoat));
        return new ResponseEntity<>(boatReservationDtos,HttpStatus.OK);
    }
    @GetMapping("/getByOwnerUsername/{username:.+}/")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<Set<QuickReservationBoatDto>> getByOwnerUsername (@PathVariable("username") String username) {
        Set<QuickReservationBoatDto> boatReservationDtos=new HashSet<>();
        for(QuickReservationBoat quickReservationBoat: quickReservationBoatService.findReservationsByOwnerUsername(username)){
            boatReservationDtos.add(quickReservationBoatMapper.boatQuickReservationToDto(quickReservationBoat));
        }
        return new ResponseEntity<>(boatReservationDtos,HttpStatus.OK);
    }
    @GetMapping("/getPastQuickReservations/{username:.+}/")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<Set<QuickReservationBoatDto>> getPastReservations (@PathVariable("username") String username) {

        Set<QuickReservationBoatDto> boatReservationDtos=new HashSet<>();
        for(QuickReservationBoat quickReservationBoat: quickReservationBoatService.getPastReservations(username)){
            boatReservationDtos.add(quickReservationBoatMapper.boatQuickReservationToDto(quickReservationBoat));
        }
        return new ResponseEntity<>(boatReservationDtos,HttpStatus.OK);
    }
    @PostMapping("/ownerCreatesReview/{reservationId}")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<String> writeAReview (@PathVariable("reservationId") Long reservationId, @RequestBody OwnersReportDto ownersReportDto) {
        QuickReservationBoat reservation=quickReservationBoatService.getById(reservationId);
        BoatQuickReservationReport reservationReport=new BoatQuickReservationReport(ownersReportDto.getId(),
                ownersReportDto.isBadComment(),ownersReportDto.getComment(),ownersReportDto.getOwnersUsername(),
                ownersReportDto.getClientUsername(),ownersReportDto.isApproved(),reservation);
        boatOwnersQuickReservationReportService.save(reservationReport);
        reservation.setSuccessfull(ownersReportDto.isSuccess());
        reservation.setOwnerWroteAReport(true);
        quickReservationBoatService.save(reservation);
        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }
}
