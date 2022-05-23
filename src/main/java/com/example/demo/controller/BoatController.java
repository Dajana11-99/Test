package com.example.demo.controller;
import com.example.demo.dto.BoatDto;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.mapper.AdditionalServiceMapper;
import com.example.demo.mapper.BoatMapper;
import com.example.demo.model.Boat;
import com.example.demo.model.BoatOwner;
import com.example.demo.service.BoatOwnerService;
import com.example.demo.service.BoatService;
import com.example.demo.service.BoatSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/boats", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoatController {
    private static final String SUCCESS ="Success";
    private static final String BAD_REQUEST ="Bad request";

    @Autowired
    private BoatService boatService;
    @Autowired
    private BoatOwnerService boatOwnerService;
    @Autowired
    private BoatSubscriptionService boatSubscriptionService;

    private final BoatMapper boatMapper=new BoatMapper();
    private final AdditionalServiceMapper additionalServiceMapper=new AdditionalServiceMapper();

    @PreAuthorize("hasRole('BOATOWNER')")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody BoatDto boatDto){
        Boat boat=boatMapper.boatDtoToBoat(boatDto);
        boat.setBoatOwner(boatOwnerService.findByUsername(boatDto.getOwnersUsername()));
        if(boatService.addNewBoat(boat,additionalServiceMapper
                .additionalServicesDtoToAdditionalServices(boatDto.getAdditionalServices()))) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Boat with that name already exists.", HttpStatus.BAD_REQUEST);
        }

    }
    @PreAuthorize("hasRole('BOATOWNER')")
    @PostMapping("/findBoatsByOwnersUsername")
    public ResponseEntity<Set<BoatDto>> getByOwnerId(@RequestBody UserRequestDTO owner){
        Set<BoatDto> boats=new HashSet<>();
        for(Boat boat: boatService.findByOwnersId(boatOwnerService.findByUsername(owner.getUsername()).getId()))
            boats.add(boatMapper.boatToBoatDto(boat));
        return new ResponseEntity<>(boats,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('BOATOWNER')")
    @PostMapping("/edit")
    public ResponseEntity<String> edit(@RequestBody BoatDto boatDto){
        BoatOwner owner=boatOwnerService.findByUsername(boatDto.getOwnersUsername());
        Boat boat=boatMapper.boatDtoToBoatEdit(boatDto);
        boat.setBoatOwner(owner);
        boolean deleteOldImages= boatDto.getImages() == null;
        if(boatService.edit(boat,deleteOldImages))
            return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
        else
            return new ResponseEntity<>("You can't edit this boat because reservations exists !",HttpStatus.BAD_REQUEST);
    }
    @PreAuthorize("hasRole('BOATOWNER')  || hasRole('ADMIN')")
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody BoatDto boatDto){
        Boat boat=boatMapper.boatDtoToBoat(boatDto);
        if(boatService.delete(boat.getId()))
            return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
        else
            return new ResponseEntity<>("You can't delete this boat because reservations exists!", HttpStatus.BAD_REQUEST);

    }
    @PreAuthorize("hasRole('BOATOWNER')")
    @PostMapping("/findByNameAndOwnersUsername/{boatName}/{username:.+}/")
    public ResponseEntity<BoatDto> findByNameAndOwnersId(@PathVariable ("boatName") String boatName,@PathVariable ("username")String username){
        Boat boat=boatService.findByNameAndOwner(boatName, boatOwnerService.findByUsername(username).getId());
        if(boat != null)
            return new ResponseEntity<>(boatMapper.boatToBoatDto(boat),HttpStatus.OK);
        else
            return new ResponseEntity<>(new BoatDto(),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Set<BoatDto>> getAll(){
        Set<BoatDto> boats=new HashSet<>();
        for(Boat boat: boatService.findAll())
            boats.add(boatMapper.boatToBoatDto(boat));
        return new ResponseEntity<>(boats,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('BOATOWNER')  || hasRole('ADMIN')")
    @PostMapping("/canBeEditedOrDeleted/{id}")
    public ResponseEntity<Boolean> canBeEditedOrDeleted(@PathVariable ("id") Long id ){
            return new ResponseEntity<>(boatService.canBeEditedOrDeleted(id),HttpStatus.OK);
    }

    @PostMapping("/findById")
    public ResponseEntity<BoatDto> findById(@RequestBody BoatDto boatDto){
        Boat boat = boatService.findById(boatDto.getId());
        if(boat != null){
            String clientUsername = boatDto.getOwnersUsername();
            boatDto = boatMapper.boatToBoatDto(boat);
            if(!clientUsername.equals(""))
                boatDto.setSubscription(boatSubscriptionService.checkIfUserIsSubscribed(clientUsername, boatDto.getId()));
            return new ResponseEntity<>(boatDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new BoatDto(),HttpStatus.BAD_REQUEST);
    }
}
