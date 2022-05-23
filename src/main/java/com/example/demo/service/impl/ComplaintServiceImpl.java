package com.example.demo.service.impl;

import com.example.demo.dto.NewComplaintDto;
import com.example.demo.mail.ComplaintOwnersInfo;
import com.example.demo.mail.MailService;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.CabinOwnerService;
import com.example.demo.service.ClientService;
import com.example.demo.service.ComplaintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {


    private final Logger logger= LoggerFactory.getLogger(FirebaseServiceImpl.class);
    @Autowired
    private CabinReservationRepository cabinReservationRepository;
    @Autowired
    private BoatReservationRepository boatReservationRepository;
    @Autowired
    private AdventureReservationRepository adventureReservationRepository;
    @Autowired
    private CabinRepository cabinRepository;
    @Autowired
    private BoatRepository boatRepository;
    @Autowired
    private CabinComplaintRepository cabinComplaintRepository;
    @Autowired
    private CabinOwnerComplaintRepository cabinOwnerComplaintRepository;
    @Autowired
    private BoatComplaintRepository boatComplaintRepository;
    @Autowired
    private BoatOwnerComplaintRepository boatOwnerComplaintRepository;
    @Autowired
    private FishingInstructorComplaintRepository fishingInstructorComplaintRepository;
    @Autowired
    private ComplaintRepository complaintRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CabinOwnerService cabinOwnerService;
    @Autowired
    private MailService mailService;

    @Override
    public boolean addComplaint(NewComplaintDto newComplaintDto) {
        if(!checkIfComplaintIsPossible(newComplaintDto)){
            return false;
        }
        saveComplaint(newComplaintDto);
        return true;
    }

    @Override
    public List<Complaint> getAll() {
        return complaintRepository.getAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public boolean sendMailAboutComplaint(Complaint complaint, String response)
    {
        complaint.setResponded(true);
        try {
            complaintRepository.save(complaint);
        }catch (ObjectOptimisticLockingFailureException e){
            return false;
        }
        if(complaint.getComplaintType().equals("CABIN_COMPLAINT")){
            String name= cabinComplaintRepository.getById(complaint.getId()).getCabin().getName();
            sendMailNotificationForCabinAndBoat(complaint,name,"cabin",response);
            sendMailNotificationForClient(complaint,name,response);
        }else if(complaint.getComplaintType().equals("BOAT_COMPLAINT")){
            Boat boat= boatComplaintRepository.getById(complaint.getId()).getBoat();
            sendMailNotificationForCabinAndBoat(complaint,boat.getName(),"boat",response);
            sendMailNotificationForClient(complaint,boat.getName(),response);
        }else {
            sendMailNotificationForOwners(complaint,response);
            sendMailNotificationForClient(complaint,complaint.getOwnersUsername(),response);
        }
        return  true;
    }

    @Override
    public Complaint getOne(Long id) {
        return  complaintRepository.getById(id);
    }

    private boolean checkIfComplaintIsPossible(NewComplaintDto newComplaintDto){
        if(newComplaintDto.getSubjectRole().equals("cabin")){
            CabinReservation cabinReservation = cabinReservationRepository.getById(newComplaintDto.getReservationId());
            return cabinReservationRepository.clientHasReservationInCabin(cabinReservation.getCabin().getId(), clientService.findByUsername(newComplaintDto.getClientUsername()).getId());
        }else if(newComplaintDto.getSubjectRole().equals("cabin owner")){
            CabinReservation cabinReservation = cabinReservationRepository.getById(newComplaintDto.getReservationId());
            CabinOwner cabinOwner = cabinReservation.getCabin().getCabinOwner();
            return cabinReservationRepository.clientHasReservationInOwnersCabin(cabinRepository.findCabinsIdByOwnersId(cabinOwnerService.findByUsername(cabinOwner.getUsername()).getId()), clientService.findByUsername(newComplaintDto.getClientUsername()).getId());
        }else if(newComplaintDto.getSubjectRole().equals("boat")){
            BoatReservation boatReservation = boatReservationRepository.getById(newComplaintDto.getReservationId());
            return boatReservationRepository.clientHasReservationInBoat(boatReservation.getBoat().getId(), clientService.findByUsername(newComplaintDto.getClientUsername()).getId());
        }else if(newComplaintDto.getSubjectRole().equals("boat owner")){
            BoatReservation boatReservation = boatReservationRepository.getById(newComplaintDto.getReservationId());
            BoatOwner boatOwner = boatReservation.getBoat().getBoatOwner();
            return boatReservationRepository.clientHasReservationInOwnersBoat(boatRepository.findBoatsIdByOwnersId(boatOwner.getId()), clientService.findByUsername(newComplaintDto.getClientUsername()).getId());
        }else if(newComplaintDto.getSubjectRole().equals("fishing instructor")){
            AdventureReservation adventureReservation = adventureReservationRepository.getById(newComplaintDto.getReservationId());
            FishingInstructor fishingInstructor = adventureReservation.getFishingInstructor();
            return adventureReservationRepository.clientHasReservationWithInstructor(fishingInstructor.getId(), clientService.findByUsername(newComplaintDto.getClientUsername()).getId());
        }
        return false;
    }
    private void saveComplaint(NewComplaintDto newComplaintDto){
        if(newComplaintDto.getSubjectRole().equals("cabin")){
            CabinReservation cabinReservation = cabinReservationRepository.getById(newComplaintDto.getReservationId());
           cabinComplaintRepository.save(new CabinComplaint(null, newComplaintDto.getText(), LocalDateTime.now(), false, clientService.findByUsername(newComplaintDto.getClientUsername()),cabinReservation.getOwnersUsername(), cabinReservation.getCabin()));
        }else if(newComplaintDto.getSubjectRole().equals("cabin owner")){
            CabinReservation cabinReservation = cabinReservationRepository.getById(newComplaintDto.getReservationId());
           cabinOwnerComplaintRepository.save(new CabinOwnerComplaint(null, newComplaintDto.getText(), LocalDateTime.now(), false, clientService.findByUsername(newComplaintDto.getClientUsername()),cabinReservation.getOwnersUsername(), cabinReservation.getCabin().getCabinOwner()));
        }else if(newComplaintDto.getSubjectRole().equals("boat")){
            BoatReservation boatReservation = boatReservationRepository.getById(newComplaintDto.getReservationId());
            boatComplaintRepository.save(new BoatComplaint(null, newComplaintDto.getText(), LocalDateTime.now(), false, clientService.findByUsername(newComplaintDto.getClientUsername()),boatReservation.getOwnersUsername(), boatReservation.getBoat()));
        }else if(newComplaintDto.getSubjectRole().equals("boat owner")){
            BoatReservation boatReservation = boatReservationRepository.getById(newComplaintDto.getReservationId());
            boatOwnerComplaintRepository.save(new BoatOwnerComplaint(null, newComplaintDto.getText(), LocalDateTime.now(), false, clientService.findByUsername(newComplaintDto.getClientUsername()),boatReservation.getOwnersUsername(), boatReservation.getBoat().getBoatOwner()));
        }
        else if(newComplaintDto.getSubjectRole().equals("fishing instructor")){
            AdventureReservation adventureReservation = adventureReservationRepository.getById(newComplaintDto.getReservationId());
            fishingInstructorComplaintRepository.save(new FishingInstructorComplaint(null, newComplaintDto.getText(), LocalDateTime.now(), false, clientService.findByUsername(newComplaintDto.getClientUsername()),adventureReservation.getOwnersUsername(), adventureReservation.getFishingInstructor()));
        }
    }
    private void sendMailNotificationForCabinAndBoat(Complaint complaint,String name,String type,String response) {

        {
            try {
                String message = "Client " + complaint.getClient().getUsername() + " complained on your " + type + " " + name + " "
                        + ". \n" +
                        "Client comment: " + complaint.getText() + "."
                        + ". \n" +
                        "Admin's response: " + response + ".";
                mailService.sendMail(complaint.getOwnersUsername(), message, new ComplaintOwnersInfo());

            } catch (MessagingException e) {
                logger.error(e.toString());
            }
        }
    }
    private void sendMailNotificationForOwners(Complaint complaint,String response){
        {
            try {
                String message = "Client " + complaint.getClient().getUsername() + " complained on you "
                        + ". " + " Client comment: " + complaint.getText() + "."
                        + ". \n" +
                        "Admin's response: " + response + ".";

                mailService.sendMail(complaint.getOwnersUsername(), message, new ComplaintOwnersInfo());

            } catch (MessagingException e) {
                logger.error(e.toString());
            }
        }
    }

    private void sendMailNotificationForClient(Complaint complaint,String name,String response){
        {
            try {
                String message =
                        "Admin has responded to your complaint for " + " " + name + ". " +
                                "\n" + "\n"
                                + "Admin's response: " + response + ". \n";

                mailService.sendMail(complaint.getClient().getUsername(), message, new ComplaintOwnersInfo());

            } catch (MessagingException e) {
                logger.error(e.toString());
            }
        }
    }

}
