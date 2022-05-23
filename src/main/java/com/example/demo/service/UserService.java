package com.example.demo.service;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.model.*;

import javax.mail.MessagingException;
import java.util.List;


public interface UserService {
    User findByUsername(String username);
    List<User> findAll ();
    List<User> getNewUsers ();
    User registerCabinOwner(CabinOwner cabinOwner);
    User registerBoatOwner(BoatOwner boatOwner);
    User registerClient(Client client,String header);

    User registerAdmin(Admin admin);
    User registerFishingInstructor(FishingInstructor fishingInstructor);


    User activateAccount(String email, String code);

    void acceptAccount(User user);
    void denyAccount(User user,String reason);

    void editUser(UserRequestDTO userRequest);

    String deleteUser(User user);

    void saveDeleteAccountRequest(String username, String reasonForDeleting);

    List<User> getAllRequestsForDeletingAccount();

    boolean sendDenyReason(String response, String recipient) throws MessagingException;

    boolean sendAcceptReason(String response, String recipient) throws MessagingException;

    String getUsernameFromToken(String s);

    void save(User user);

    Double findRatingByUsername(String username);

    void updateOwnersRating(String username,Double grade);
}