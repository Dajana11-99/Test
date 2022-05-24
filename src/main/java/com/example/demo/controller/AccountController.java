package com.example.demo.controller;
import com.example.demo.dto.MailDto;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.VerificationDTO;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;

@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private static final String SUCCESS = "Success!";

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;


    @PostMapping("/acceptAccount")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> acceptAccount(@RequestBody UserRequestDTO userRequest){
        userService.acceptAccount(userService.findByUsername(userRequest.getUsername()));

        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }
    @PostMapping("/denyAccount/{reason}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> denyAccount(@PathVariable ("reason") String reason, @RequestBody UserRequestDTO userRequest) {
        userService.denyAccount(userService.findByUsername(userRequest.getUsername()),reason);
        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }


    @PostMapping("/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> activate(@RequestBody VerificationDTO verificationDTO) {
        String email = verificationDTO.getEmail();
        String code = verificationDTO.getActivationCode();
        if(this.userService.activateAccount(email, code) != null){
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/passwordStatus")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> hasAlreadyResetPassword(@RequestBody UserRequestDTO userRequest) {
        Boolean passwordStatus= adminService.hasAlreadyResetPassword(userRequest.getUsername());
        return new ResponseEntity<>(passwordStatus, HttpStatus.OK);
    }

    @PostMapping("/sendDenyReasonForDeletingAccount")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> sendDenyReasonForDeletingAccount(@RequestBody MailDto mailDto) {
        try {
            if(!userService.sendDenyReason(mailDto.getResponse(),mailDto.getRecipient())){
                return new ResponseEntity<>("Reason is already sent!", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }catch (ObjectOptimisticLockingFailureException e){
            return new ResponseEntity<>("Reason is already sent!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sendAcceptReasonForDeletingAccount")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> sendAcceptReasonForDeletingAccount(@RequestBody MailDto mailDto) throws MessagingException {
       try {
           if(!userService.sendAcceptReason(mailDto.getResponse(),mailDto.getRecipient())){
               return new ResponseEntity<>("User is already deleted!", HttpStatus.BAD_REQUEST);
           }
           return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>("User is already deleted!", HttpStatus.BAD_REQUEST);

       }

    }



}
