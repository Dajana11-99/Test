package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.fisherman.dto.NewComplaintDto;
import rs.ac.uns.ftn.isa.fisherman.service.ComplaintService;

@RestController
@RequestMapping(value = "/complaint", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/addComplaint")
    public ResponseEntity<String> addComplaint(@RequestBody NewComplaintDto newComplaintDto){
        if(complaintService.addComplaint(newComplaintDto)){
            return new ResponseEntity<>("Complaint successfully added.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("You must have at least one reservation to post this complaint!", HttpStatus.BAD_REQUEST);
        }
    }
}
