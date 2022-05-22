package com.example.demo.service;


import com.example.demo.model.OwnersReport;

import java.util.List;
import java.util.Set;

public interface OwnersReportService {
    List<OwnersReport> getAllUnApprovedReports();

    void sendReviewResponse(String clientUsername, String ownersUsername, String comment);

    OwnersReport setReviewStatus(Long id);
    List<OwnersReport> findAll();
      OwnersReport findById(Long id);
}
