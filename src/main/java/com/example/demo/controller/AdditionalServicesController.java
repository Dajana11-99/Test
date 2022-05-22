package com.example.demo.controller;
import com.example.demo.dto.AdditionalServicesDto;
import com.example.demo.mapper.AdditionalServiceMapper;
import com.example.demo.model.AdditionalServices;
import com.example.demo.service.AdditionalServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/services", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdditionalServicesController {
    @Autowired
    private AdditionalServicesService additionalServicesService;
    private final AdditionalServiceMapper additionalServiceMapper = new AdditionalServiceMapper();

    @PostMapping("/findById")
    public ResponseEntity<AdditionalServicesDto> findById(@RequestBody AdditionalServicesDto additionalServicesDto){

        AdditionalServices additionalServices=additionalServicesService.findById(additionalServicesDto.getId());
        AdditionalServicesDto additionalServicesDto1=additionalServiceMapper.additionalServiceToAdditionalServiceDto(additionalServices);
        return new ResponseEntity<>(additionalServicesDto1,HttpStatus.OK);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<AdditionalServicesDto>> findAll(){
        List<AdditionalServicesDto> additionalServices=new ArrayList<>();
        for(AdditionalServices additionalServices1: additionalServicesService.getAll())
            additionalServices.add(additionalServiceMapper.additionalServiceToAdditionalServiceDto(additionalServices1));
        return new ResponseEntity<>(additionalServices,HttpStatus.OK);
    }
}
