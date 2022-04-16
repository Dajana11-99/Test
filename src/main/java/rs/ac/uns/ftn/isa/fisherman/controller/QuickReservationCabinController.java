package rs.ac.uns.ftn.isa.fisherman.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.OwnersReportDto;
import rs.ac.uns.ftn.isa.fisherman.dto.QuickReservationCabinDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.QuickReservationCabinMapper;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.service.CabinOwnerService;
import rs.ac.uns.ftn.isa.fisherman.service.CabinOwnersQuickReservationReportService;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationCabinService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/quickReservationCabin", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuickReservationCabinController {
    @Autowired
    private QuickReservationCabinService quickReservationCabinService;
    @Autowired
    private CabinOwnerService cabinOwnerService;
    @Autowired
    private CabinOwnersQuickReservationReportService cabinOwnersQuickReservationReportService;
    private final QuickReservationCabinMapper quickReservationCabinMapper=new QuickReservationCabinMapper();

    @PostMapping("/ownerCreates")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<String> ownerCreates (@RequestBody QuickReservationCabinDto quickReservationCabinDto) {
        QuickReservationCabin quickReservationCabin= quickReservationCabinMapper.dtoToQuickReservation(quickReservationCabinDto);
        if(quickReservationCabinService.ownerCreates(quickReservationCabin)){
            return new ResponseEntity<>("Success.", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Unsuccessfull reservation.", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value= "/getByCabinId/{cabinId}")
    public ResponseEntity<Set<QuickReservationCabinDto>> getPresentByCabinId(@PathVariable ("cabinId") Long cabinId) {
        Set<QuickReservationCabinDto> quickReservationCabinDtos= new HashSet<>();
        for(QuickReservationCabin quickReservationCabin: quickReservationCabinService.getByCabinId(cabinId))
            quickReservationCabinDtos.add(quickReservationCabinMapper.quickReservationToDto(quickReservationCabin));
        return new ResponseEntity<>(quickReservationCabinDtos,HttpStatus.OK);
    }
    @GetMapping("/getByOwnerUsername/{username:.+}/")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<Set<QuickReservationCabinDto>> getByOwnerUsername (@PathVariable("username") String username) {
        Set<QuickReservationCabinDto> cabinReservationDtos=new HashSet<>();
        for(QuickReservationCabin quickReservationCabin: quickReservationCabinService.findReservationsByOwnerId(username)){
            cabinReservationDtos.add(quickReservationCabinMapper.quickReservationToDto(quickReservationCabin));
        }
        return new ResponseEntity<>(cabinReservationDtos,HttpStatus.OK);
    }
    @GetMapping("/getPastQuickReservations/{username:.+}/")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<Set<QuickReservationCabinDto>> getPastReservations (@PathVariable("username") String username) {
        Set<QuickReservationCabinDto> cabinReservationDtos=new HashSet<>();
        for(QuickReservationCabin quickReservationCabin: quickReservationCabinService.getPastReservations(username)){
            cabinReservationDtos.add(quickReservationCabinMapper.quickReservationToDto(quickReservationCabin));
        }
        return new ResponseEntity<>(cabinReservationDtos,HttpStatus.OK);
    }
    @PostMapping("/ownerCreatesReview/{reservationId}")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<String> writeAReview (@PathVariable("reservationId") Long reservationId, @RequestBody OwnersReportDto ownersReportDto) {
        QuickReservationCabin reservation=quickReservationCabinService.findReservationById(reservationId);
        CabinQuickReservationReport reservationReport=new CabinQuickReservationReport(ownersReportDto.getId(),
                ownersReportDto.isBadComment(),ownersReportDto.getComment(),ownersReportDto.getOwnersUsername(),
                ownersReportDto.getClientUsername(),ownersReportDto.isApproved(),reservation);
        cabinOwnersQuickReservationReportService.save(reservationReport);
        reservation.setSuccessfull(ownersReportDto.isSuccess());
        reservation.setOwnerWroteAReport(true);
        quickReservationCabinService.save(reservation);
        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }

}
