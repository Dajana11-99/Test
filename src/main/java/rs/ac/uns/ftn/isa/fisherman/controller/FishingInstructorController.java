package rs.ac.uns.ftn.isa.fisherman.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.*;
import rs.ac.uns.ftn.isa.fisherman.mapper.FishingInstructorMapper;
import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;
import rs.ac.uns.ftn.isa.fisherman.service.FishingInstructorService;

@RestController
@RequestMapping(value = "/instructors", produces = MediaType.APPLICATION_JSON_VALUE)
public class FishingInstructorController {

    @Autowired
    private FishingInstructorService fishingInstructorService;

    private final FishingInstructorMapper fishingInstructorMapper=new FishingInstructorMapper();

    @PreAuthorize("hasRole('CLIENT') || hasRole('FISHING_INSTRUCTOR')")
    @PostMapping("/findInstructorRatingByUsername")
    public ResponseEntity<Double> findInstructorRatingByUsername(@RequestBody FishingInstructorDto instructor){
       Double instructorRating= fishingInstructorService.findByUsername(instructor.getUsername()).getRating();
        return new ResponseEntity<>(instructorRating, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PostMapping("/findByUsername")
    public ResponseEntity<FishingInstructorDto> findByUsername(@RequestBody FishingInstructorDto instructor){
        FishingInstructor fishingInstructor=  fishingInstructorService.findByUsername(instructor.getUsername());
       FishingInstructorDto fishingInstructorDto= fishingInstructorMapper.fishingInstructorToFishingInstructorDto(fishingInstructor);
       return new ResponseEntity<>(fishingInstructorDto, HttpStatus.OK);
    }






}
