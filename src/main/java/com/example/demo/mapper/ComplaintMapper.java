package com.example.demo.mapper;


import com.example.demo.dto.ComplaintDto;
import com.example.demo.model.Complaint;

public class ComplaintMapper {
    public ComplaintDto complaintToComplaintDto(Complaint complaint){
        return  new ComplaintDto(complaint.getId(),complaint.getText(),complaint.getDate(),complaint.isResponded(),complaint.getClient().getUsername(),complaint.getOwnersUsername(),complaint.getComplaintType());
    }

}
