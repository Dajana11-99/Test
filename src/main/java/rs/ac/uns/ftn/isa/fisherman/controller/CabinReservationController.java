package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.*;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinReservationMapper;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinMapper;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.service.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/reservationCabin", produces = MediaType.APPLICATION_JSON_VALUE)
public class CabinReservationController {

    @Autowired
    private ReservationCabinService reservationCabinService;
    @Autowired
    private CabinOwnerService cabinOwnerService;
    @Autowired
    private CabinReservationCancellationService cabinReservationCancellationService;
    @Autowired
    private CabinOwnersReservationReportService cabinOwnersReservationReportService;
    @Autowired
    private PenaltyService penaltyService;

    private final CabinMapper cabinMapper = new CabinMapper();
    private final CabinReservationMapper cabinReservationMapper=new CabinReservationMapper();

    @PostMapping("/getAvailableCabins")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Set<CabinDto>> getAvailableCabins (@RequestBody SearchAvailablePeriodsCabinDto searchAvailablePeriodsCabinDto) {
        Set<CabinDto> cabinsDto= new HashSet<>();
        for(Cabin cabin:reservationCabinService.getAvailableCabins(searchAvailablePeriodsCabinDto)){
            cabinsDto.add(cabinMapper.cabinToCabinDto(cabin));
        }
        return new ResponseEntity<>(cabinsDto, HttpStatus.OK);
    }

    @PostMapping("/makeReservation")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> makeReservation (@RequestBody CabinReservationDto cabinReservationDto) {
        if(penaltyService.isUserBlockedFromReservation(cabinReservationDto.getClientUsername()))
            return new ResponseEntity<>("Client banned from making reservations!", HttpStatus.BAD_REQUEST);
        if(cabinReservationCancellationService.clientHasCancellationForCabinInPeriod(cabinReservationDto))
            return new ResponseEntity<>("Client has cancellation for cabin in given period!", HttpStatus.BAD_REQUEST);
        if(reservationCabinService.makeReservation(cabinReservationDto))
            return new ResponseEntity<>("Success.", HttpStatus.OK);
        else
            return new ResponseEntity<>("Unsuccessful reservation.", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/ownerCreates")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<String> ownerCreates (@RequestBody CabinReservationDto cabinReservationDto) {
        CabinOwner cabinOwner = cabinOwnerService.findByUsername(cabinReservationDto.getCabinDto().getOwnerUsername());
        CabinReservation cabinReservation= cabinReservationMapper.cabinOwnerReservationDtoToCabinReservation(cabinReservationDto);
        cabinReservation.getCabin().setCabinOwner(cabinOwner);
        if(reservationCabinService.ownerCreates(cabinReservation,cabinReservationDto.getClientUsername())) {

            return new ResponseEntity<>("Success.", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Unsuccessful reservation.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value= "/getByCabinId/{cabinId}")
    public ResponseEntity<Set<CabinReservationDto>> getPresentByCabinId(@PathVariable ("cabinId") Long cabinId) {
        Set<CabinReservationDto> cabinReservationDtos= new HashSet<>();
        for(CabinReservation cabinReservation: reservationCabinService.getPresentByCabinId(cabinId))
            cabinReservationDtos.add(cabinReservationMapper.cabinReservationToCabinReservationDto(cabinReservation));
        return new ResponseEntity<>(cabinReservationDtos,HttpStatus.OK);
    }

    @PostMapping(value= "/getUpcomingReservations")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Set<CabinReservationDto>> getUpcomingReservations(@RequestBody UserRequestDTO userRequestDTO) {
        Set<CabinReservationDto> cabinReservationDtos= new HashSet<>();
        for(CabinReservation cabinReservation: reservationCabinService.getUpcomingClientReservationsByUsername(userRequestDTO.getUsername()))
            cabinReservationDtos.add(cabinReservationMapper.cabinReservationToCabinReservationDto(cabinReservation));
        return new ResponseEntity<>(cabinReservationDtos,HttpStatus.OK);
    }

    @PostMapping(value= "/getReservationsHistory")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Set<CabinReservationDto>> getReservationsHistory(@RequestBody UserRequestDTO userRequestDTO) {
        Set<CabinReservationDto> cabinReservationDtos= new HashSet<>();
        for(CabinReservation cabinReservation: reservationCabinService.getClientReservationHistoryByUsername(userRequestDTO.getUsername())){
            CabinReservationDto cabinReservationDto = cabinReservationMapper.cabinReservationToCabinReservationDto(cabinReservation);
            cabinReservationDtos.add(cabinReservationDto);
        }
        return new ResponseEntity<>(cabinReservationDtos,HttpStatus.OK);
    }

    @PostMapping("/cancelReservation")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> cancelReservation (@RequestBody CabinReservationDto cabinReservationDto) {
        if(cabinReservationCancellationService.addCancellation(cabinReservationDto))
            return new ResponseEntity<>("Successful cancellation.", HttpStatus.OK);
        else
            return new ResponseEntity<>("Unsuccessful cancellation.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getByOwnerUsername/{username:.+}/")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<Set<CabinReservationDto>> getByOwnerUsername (@PathVariable("username") String username) {

        Set<CabinReservationDto> cabinReservationDtos=new HashSet<>();
        for(CabinReservation cabinReservation: reservationCabinService.findReservationsByOwnerUsername(username)){
            cabinReservationDtos.add(cabinReservationMapper.cabinReservationToCabinReservationDto(cabinReservation));
        }
        return new ResponseEntity<>(cabinReservationDtos,HttpStatus.OK);
    }

    @GetMapping("/getPastReservations/{username:.+}/")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<Set<CabinReservationDto>> getPastReservations (@PathVariable("username") String username) {

        Set<CabinReservationDto> cabinReservationDtos=new HashSet<>();
        for(CabinReservation cabinReservation: reservationCabinService.getPastReservations(username)){
            cabinReservationDtos.add(cabinReservationMapper.cabinReservationToCabinReservationDto(cabinReservation));
        }
        return new ResponseEntity<>(cabinReservationDtos,HttpStatus.OK);
    }
    @PostMapping("/ownerCreatesReview/{reservationId}")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<String> writeAReview (@PathVariable("reservationId") Long reservationId, @RequestBody OwnersReportDto ownersReportDto) {
        CabinReservation reservation=reservationCabinService.getById(reservationId);
        CabinOwnersReservationReport reservationReport=new CabinOwnersReservationReport(ownersReportDto.getId(),
                ownersReportDto.isBadComment(),ownersReportDto.getComment(),ownersReportDto.getOwnersUsername(),
                ownersReportDto.getClientUsername(),ownersReportDto.isApproved(),reservation);
        cabinOwnersReservationReportService.save(reservationReport);
        reservation.setSuccessfull(ownersReportDto.isSuccess());
        reservation.setOwnerWroteAReport(true);
        reservationCabinService.save(reservation);
        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }

        @GetMapping(value= "/getById/{id}")
        public ResponseEntity<CabinReservationDto> getById(@PathVariable ("id") Long id) {
            return new ResponseEntity<>(cabinReservationMapper.cabinReservationToCabinReservationDto(reservationCabinService.getById(id)),HttpStatus.OK);
        }
}
