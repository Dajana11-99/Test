package rs.ac.uns.ftn.isa.fisherman.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.mapper.*;
import rs.ac.uns.ftn.isa.fisherman.model.User;
import rs.ac.uns.ftn.isa.fisherman.service.UserService;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/userc", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    private final UserMapper userMapper=new UserMapper();

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Iterable<UserRequestDTO>> getAllUsers(){
        List<UserRequestDTO> allUsers=new ArrayList<>();
        for(User user: userService.findAll())
            allUsers.add(userMapper.userToUserRequestDTO(user));
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    @GetMapping("/getNewUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<List<UserRequestDTO>> getNewUsers() {
        List<UserRequestDTO> userRequestDtos = new ArrayList<>();
        for(User user :userService.getNewUsers())
            userRequestDtos.add(userMapper.userToUserRequestDTO(user));
        return new ResponseEntity<>(userRequestDtos, HttpStatus.OK);
    }
    @PostMapping("/deleteUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@RequestBody UserRequestDTO userRequest) {
        User user= userService.findByUsername(userRequest.getUsername());
        userService.deleteUser(user);
        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }
    @GetMapping(value = "getUsername")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public @ResponseBody String getUsernameFromToken(@RequestHeader("Authorization") String token) {
        return userService.getUsernameFromToken(token.split(" ")[1]);
    }
    @GetMapping("/getAllRequests")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserRequestDTO>> getAllRequestsForDeletingAccount() {
        List<UserRequestDTO> users= new ArrayList<>();
        for(User user : userService.getAllRequestsForDeletingAccount())
            users.add(userMapper.userToDeleteUserRequestDTO(user));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
