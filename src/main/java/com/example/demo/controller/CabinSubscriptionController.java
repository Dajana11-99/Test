package com.example.demo.controller;

import com.example.demo.dto.CabinSubscriptionDto;
import com.example.demo.mapper.CabinSubscriptionMapper;
import com.example.demo.model.CabinSubscription;
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
@RequestMapping(value = "/cabinSubscription", produces = MediaType.APPLICATION_JSON_VALUE)
public class CabinSubscriptionController {

    @Autowired
    CabinSubscriptionService cabinSubscriptionService;

    final private CabinSubscriptionMapper cabinSubscriptionMapper = new CabinSubscriptionMapper();

    @PostMapping("/addSubscription")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> addSubscription (@RequestBody CabinSubscriptionDto cabinSubscriptionDto) {
        cabinSubscriptionService.addSubscription(cabinSubscriptionDto.getClientUsername(), cabinSubscriptionDto.getCabinDto().getId());
        return new ResponseEntity<>("Successful subscription!", HttpStatus.OK);
    }

    @PostMapping("/removeSubscription")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> removeSubscription (@RequestBody CabinSubscriptionDto cabinSubscriptionDto) {
        cabinSubscriptionService.removeSubscription(cabinSubscriptionDto.getClientUsername(), cabinSubscriptionDto.getCabinDto().getId());
        return new ResponseEntity<>("Subscription successfully removed!", HttpStatus.OK);
    }

    @GetMapping("/getByClientUsername/{username:.+}/")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Set<CabinSubscriptionDto>> getByClientUsername (@PathVariable("username") String username) {
        Set<CabinSubscriptionDto> cabinSubscriptionDtos=new HashSet<>();
        for(CabinSubscription cabinSubscription: cabinSubscriptionService.findSubscriptionsByClientUsername(username)){
            cabinSubscriptionDtos.add(cabinSubscriptionMapper.cabinSubscriptionToCabinSubscriptionDto(cabinSubscription));
        }
        return new ResponseEntity<>(cabinSubscriptionDtos,HttpStatus.OK);
    }
}
