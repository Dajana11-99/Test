package com.example.demo.controller;

import com.example.demo.dto.PenaltyDto;
import com.example.demo.mapper.PenaltyMapper;
import com.example.demo.service.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/penalty", produces = MediaType.APPLICATION_JSON_VALUE)
public class PenaltyController {

    @Autowired
    private PenaltyService penaltyService;
    private PenaltyMapper penaltyMapper = new PenaltyMapper();

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/getClientPenalties/{username}")
    public ResponseEntity<Set<PenaltyDto>> getClientPenalties(@PathVariable("username") String username ){
        return new ResponseEntity<>(penaltyMapper.penaltiesToPenaltiesDto(penaltyService.getUserPenalties(username)), HttpStatus.OK);
    }

}
