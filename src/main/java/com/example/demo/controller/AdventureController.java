package com.example.demo.controller;
import com.example.demo.dto.AdventureDto;
import com.example.demo.dto.FishingInstructorDto;
import com.example.demo.mapper.AdditionalServiceMapper;
import com.example.demo.mapper.AdventureMapper;
import com.example.demo.model.AdditionalServices;
import com.example.demo.model.Adventure;
import com.example.demo.model.FishingInstructor;
import com.example.demo.service.AdventureService;
import com.example.demo.service.AdventureSubscriptionService;
import com.example.demo.service.FishingInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/adventures", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdventureController {

    private static final String SUCCESS = "Success.";
    @Autowired
    private FishingInstructorService fishingInstructorService;
    @Autowired
    private AdventureService adventureService;
    @Autowired
    private AdventureSubscriptionService adventureSubscriptionService;

    private final AdditionalServiceMapper additionalServiceMapper=new AdditionalServiceMapper();
    private final AdventureMapper adventureMapper=new AdventureMapper();

    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody AdventureDto adventureDto){
        FishingInstructor fishingInstructor =  fishingInstructorService.findByUsername(adventureDto.getFishingInstructorUsername());
        Adventure adventure=adventureMapper.adventureDtoToAdventure(adventureDto);
        adventure.setFishingInstructor(fishingInstructor);
        Set<AdditionalServices> services = new HashSet<>();
        if(adventureDto.getAdditionalServices()!= null){
                 services = additionalServiceMapper
                .additionalServicesDtoToAdditionalServices(adventureDto.getAdditionalServices());
        }
        if(adventureService.addNewAdventure(adventure,services))
            return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);

        else
            return new ResponseEntity<>("Already exists", HttpStatus.BAD_REQUEST);


    }

    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PostMapping("/findAdventuresByInstructorUsername")
    public ResponseEntity<Set<AdventureDto>> findAdventuresByInstructorUsername(@RequestBody FishingInstructorDto instructor){
        Set<AdventureDto> adventures=new HashSet<>();
        for(Adventure adventure: adventureService.findAdventuresByInstructorId(fishingInstructorService.findByUsername(instructor.getUsername()).getId()))
            adventures.add(adventureMapper.adventureToAdventureDto(adventure));
        return new ResponseEntity<>(adventures, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Set<AdventureDto>> getAll(){
        Set<AdventureDto> adventures=new HashSet<>();
        for(Adventure adventure: adventureService.findAll())
        {
            AdventureDto adventureDto = adventureMapper.adventureToAdventureDto(adventure);
            adventureDto.setInstructorRating(adventure.getFishingInstructor().getRating());
            adventures.add(adventureDto);
        }
        return new ResponseEntity<>(adventures, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PostMapping("/findByName")
    public ResponseEntity<AdventureDto> findByName(@RequestBody AdventureDto adventureDto){
        Long fishingInstructorId= fishingInstructorService.findByUsername(adventureDto.getFishingInstructorUsername()).getId();
        String adventureName= adventureDto.getName();
        Adventure adventure= adventureService.findAdventureByName(adventureName,fishingInstructorId);
        return new ResponseEntity<>(adventureMapper.adventureToAdventureDto(adventure), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')  || hasRole('ADMIN')")
    @PostMapping("/deleteAdventure")
    public ResponseEntity<String> deleteAdventure(@RequestBody AdventureDto adventureDto){
        if(adventureService.delete(adventureDto.getId())){
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>("You can't delete this adventure because reservations exists!", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PostMapping("/edit")
    public ResponseEntity<String> editAdventure(@RequestBody AdventureDto adventureDto){
        FishingInstructor fishingInstructor= fishingInstructorService.findByUsername(adventureDto.getFishingInstructorUsername());
        Adventure adventure = adventureMapper.adventureDtoToEditAdventure(adventureDto);
  try {

      if (adventureService.edit(adventure, fishingInstructor.getId()))
          return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
      else
          return new ResponseEntity<>("You can't edit this adventure because reservations exists !", HttpStatus.BAD_REQUEST);
  }catch (Exception e) {
      return new ResponseEntity<>("You can't edit this adventure because reservations exists !", HttpStatus.BAD_REQUEST);

  }
    }
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @GetMapping("/findInstructorsAdventure/{username:.+}/")
    public ResponseEntity<Set<AdventureDto>> findInstructorsAdventure(@PathVariable ("username") String username){
        Set<AdventureDto> adventures=new HashSet<>();
        FishingInstructor fishingInstructor = fishingInstructorService.findByUsername(username);
        for(Adventure adventure: adventureService.findAdventuresByInstructorId(fishingInstructor.getId()))
            adventures.add(adventureMapper.adventureToAdventureDto(adventure));
        return new ResponseEntity<>(adventures, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')  || hasRole('ADMIN')")
    @PostMapping("/canBeEditedOrDeleted/{id}")
    public ResponseEntity<Boolean> canBeEditedOrDeleted(@PathVariable ("id") Long id ){
        return new ResponseEntity<>(adventureService.canBeEditedOrDeleted(id),HttpStatus.OK);
    }

    @PostMapping("/findById")
    public ResponseEntity<AdventureDto> findById(@RequestBody AdventureDto adventureDto){
        Adventure adventure = adventureService.findById(adventureDto.getId());
        if(adventure != null){
            String clientUsername = adventureDto.getFishingInstructorUsername();
            adventureDto = adventureMapper.adventureToAdventureDto(adventure);
            adventureDto.setInstructorRating(adventure.getFishingInstructor().getRating());
            if(!clientUsername.equals(""))
                adventureDto.setSubscription(adventureSubscriptionService.checkIfUserIsSubscribed(clientUsername, adventureDto.getId()));
            return new ResponseEntity<>(adventureDto,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new AdventureDto(),HttpStatus.BAD_REQUEST);
    }
}
