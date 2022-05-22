package com.example.demo.controller;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.model.Admin;
import com.example.demo.model.User;
import com.example.demo.service.AdminService;
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
@RequestMapping(value = "/admins", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    private final AdminMapper adminMapper = new AdminMapper();

    @PostMapping("/signUpAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registerAdmin(@RequestBody UserRequestDTO userRequest) {
        User existUser = this.userService.findByUsername(userRequest.getUsername());
        if (existUser != null) {
            return new ResponseEntity<>("Email already in use.", HttpStatus.BAD_REQUEST);
        }
        Admin admin=adminMapper.userRequestDtoToAdmin(userRequest);
        admin.setRating(null);
        userService.registerAdmin(admin);
        return new ResponseEntity<>("Success.", HttpStatus.CREATED);
    }

    @GetMapping(value= "/isPredefined/{email:.+}/")
    public ResponseEntity<Boolean> isPredefined(@PathVariable ("email") String email) {
        Boolean status= adminService.isPredefined(email);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/getAllAdmins")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserRequestDTO>> getAllAdmins() {
        List<UserRequestDTO>allAdmins=new ArrayList<>();
        for (Admin admin:adminService.getAllAdmin())
            allAdmins.add(adminMapper.adminToUserRequestDTO(admin));

        return new ResponseEntity<>(allAdmins, HttpStatus.OK);
    }



}
