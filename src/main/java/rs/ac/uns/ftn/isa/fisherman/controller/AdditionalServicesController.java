package rs.ac.uns.ftn.isa.fisherman.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.AdditionalServicesDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdditionalServiceMapper;
import rs.ac.uns.ftn.isa.fisherman.model.AdditionalServices;
import rs.ac.uns.ftn.isa.fisherman.service.AdditionalServicesService;
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
