package com.example.demo.service.impl;

import com.example.demo.mail.AdminReviewResponse;
import com.example.demo.mail.MailService;
import com.example.demo.model.OwnersReport;
import com.example.demo.repository.OwnersReportRepository;
import com.example.demo.service.OwnersReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnersReportServiceImpl implements OwnersReportService {

    @Autowired
    private MailService mailService;

    private final Logger logger= LoggerFactory.getLogger(FirebaseServiceImpl.class);
 @Autowired
 private OwnersReportRepository ownersReportRepository;
    @Override
    public List<OwnersReport> getAllUnApprovedReports() {
        List<OwnersReport> unapproved= new ArrayList<>();
        for(OwnersReport ownersReport: ownersReportRepository.findAll()){

            if(ownersReport.isApproved()== false && ownersReport.isBadComment()==true)
                unapproved.add(ownersReport);
        }
        return unapproved;
    }



    @Override
    public void sendReviewResponse(String clientUsername, String ownersUsername, String comment) {
        sendMailNotification(clientUsername,comment);
        sendMailNotification(ownersUsername,comment);
    }


    @Override
    public OwnersReport setReviewStatus(Long id){
       OwnersReport ownersReport= ownersReportRepository.getById(id);
       ownersReport.setApproved(true);
       ownersReportRepository.save(ownersReport);
       return  ownersReport;
    }

    @Override
    public List<OwnersReport> findAll() {
     return  ownersReportRepository.findAll();
    }

    @Override
   public  OwnersReport findById(Long id){
        return  ownersReportRepository.getById(id);
   }

    private void sendMailNotification(String username,String adminMessage) {
        {
            try {
                String message = adminMessage;
                mailService.sendMail(username, message, new AdminReviewResponse());
            } catch (MessagingException e) {
                logger.error(e.toString());
            }

        }
    }
}
