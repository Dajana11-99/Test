package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.model.UserTokenState;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class LogInServiceImpl implements LoginService {
    @Autowired
    private  TokenUtils tokenUtils;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  UserRepository userRepository;


    public LogInServiceImpl(TokenUtils tokenUtils, UserRepository userRepository) {
        this.tokenUtils = tokenUtils;
        this.userRepository = userRepository;

    }
    public UserTokenState logIn(String username, String password) {

        System.out.println("AAAAAAA USAOOO"+ username);
        System.out.println("AAAAAAA USAOOO"+ password);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String userType= userRepository.findRoleById(user.getId());
        String accessToken = tokenUtils.generateToken(user.getUsername());
        int accessExpiresIn = tokenUtils.getExpiredIn();
        return new UserTokenState(userType,accessToken, accessExpiresIn);
    }


}
