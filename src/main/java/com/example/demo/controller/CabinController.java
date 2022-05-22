package com.example.demo.controller;

import com.example.demo.dto.CabinDto;
import com.example.demo.mapper.AdditionalServiceMapper;
import com.example.demo.mapper.CabinMapper;
import com.example.demo.model.AdditionalServices;
import com.example.demo.model.Cabin;
import com.example.demo.service.CabinOwnerService;
import com.example.demo.service.CabinService;
import com.example.demo.service.CabinSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/cabins", produces = MediaType.APPLICATION_JSON_VALUE)
public class CabinController {

    private static final String SUCCESS ="Success";

    @Autowired
    private CabinService cabinService;
    @Autowired
    private CabinOwnerService cabinOwnerService;
    @Autowired
    private CabinSubscriptionService cabinSubscriptionService;

    private final CabinMapper cabinMapper=new CabinMapper();
    private final AdditionalServiceMapper additionalServiceMapper=new AdditionalServiceMapper();

    @PreAuthorize("hasRole('CABINOWNER')")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody CabinDto cabinDto){
            Cabin cabin = cabinMapper.cabinDtoToCabin(cabinDto);
            cabin.setCabinOwner(cabinOwnerService.findByUsername(cabinDto.getOwnerUsername()));
            Set<AdditionalServices> additionalServices=new HashSet<>();
            if(cabinDto.getAdditionalServices()!=null)
                 additionalServices=additionalServiceMapper.additionalServicesDtoToAdditionalServices(cabinDto.getAdditionalServices());
            if(cabinService.save(cabin,additionalServices))
                return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
            else
                return new ResponseEntity<>("Already exists.", HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('CABINOWNER')")
    @GetMapping("/findCabinsByOwnersUsername/{username:.+}/")
    public ResponseEntity<Set<CabinDto>> getByOwnerId(@PathVariable("username") String username){
        Set<CabinDto> cabins=new HashSet<>();
        for(Cabin cabin: cabinService.findByOwnersId(cabinOwnerService.findByUsername(username).getId()))
            cabins.add(cabinMapper.cabinToCabinDto(cabin));
        return new ResponseEntity<>(cabins,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CABINOWNER')")
    @PostMapping("/findByName")
    public ResponseEntity<CabinDto> findByName(@RequestBody CabinDto cabinDto){
        CabinDto cabin= cabinMapper.cabinToCabinDto(cabinService.findByName(cabinDto.getName()));
        return new ResponseEntity<>(cabin,HttpStatus.OK);
    }
    @PostMapping("/findByNameClient")
    public ResponseEntity<CabinDto> findByNameClient(@RequestBody CabinDto cabinDto){
        CabinDto cabin= cabinMapper.cabinToCabinDto(cabinService.findByName(cabinDto.getName()));
        if(!cabinDto.getOwnerUsername().equals(""))
            cabin.setSubscription(cabinSubscriptionService.checkIfUserIsSubscribed(cabinDto.getOwnerUsername(), cabin.getId()));
        return new ResponseEntity<>(cabin,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('CABINOWNER')|| hasRole('ADMIN')")
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody CabinDto cabinDto){
        Cabin cabin=cabinMapper.cabinDtoToCabin(cabinDto);
        cabinService.delete(cabin.getId());
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('CABINOWNER')")
    @PostMapping("/edit")
    public ResponseEntity<String> edit(@RequestBody CabinDto cabinDto){
        Cabin cabin=cabinMapper.cabinDtoEditToCabin(cabinDto);
        boolean deleteOldImages= cabinDto.getImages() == null;
        cabinService.edit(cabin,deleteOldImages);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Set<CabinDto>> getAll(){
        Set<CabinDto> cabins=new HashSet<>();
        for(Cabin cabin: cabinService.findAll())
            cabins.add(cabinMapper.cabinToCabinDto(cabin));
        return new ResponseEntity<>(cabins,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('CABINOWNER')|| hasRole('ADMIN')")
    @PostMapping("/canBeEditedOrDeleted/{id}")
    public ResponseEntity<Boolean> canBeEditedOrDeleted(@PathVariable ("id") Long id ){
        return new ResponseEntity<>(cabinService.canBeEditedOrDeleted(id),HttpStatus.OK);
    }
}
