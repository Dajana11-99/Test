package com.example.demo.controller;

import com.example.demo.dto.ComplaintDto;
import com.example.demo.dto.NewComplaintDto;
import com.example.demo.mapper.ComplaintMapper;
import com.example.demo.model.Complaint;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/complaint", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private UserService userService;

    private ComplaintMapper complaintMapper = new ComplaintMapper();

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/addComplaint")
    public ResponseEntity<String> addComplaint(@RequestBody NewComplaintDto newComplaintDto){
        if(complaintService.addComplaint(newComplaintDto)){
            return new ResponseEntity<>("Complaint successfully added.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("You must have at least one reservation to post this complaint!", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<ComplaintDto>> getAll(){
       List<ComplaintDto> complaints=new ArrayList<>();
       for (Complaint complaint:  complaintService.getAll())
           complaints.add(complaintMapper.complaintToComplaintDto(complaint));
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/sendMailAboutComplaint/{id}")
    public ResponseEntity<String> sendMailAboutComplaint(@PathVariable ("id") Long id,@RequestBody String response){
        Complaint complaint = complaintService.getOne(id);
        System.out.println("res"+response);
        complaintService.sendMailAboutComplaint(complaint,response);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
