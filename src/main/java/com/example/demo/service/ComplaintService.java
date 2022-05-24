package com.example.demo.service;


import com.example.demo.dto.NewComplaintDto;
import com.example.demo.model.Complaint;

import java.util.List;

public interface ComplaintService {

    boolean addComplaint(NewComplaintDto newComplaintDto);

    List<Complaint> getAll();

    boolean sendMailAboutComplaint(Complaint complaint,String response) throws Exception;

    Complaint getOne(Long id);
}
