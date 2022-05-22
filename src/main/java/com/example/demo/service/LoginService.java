package com.example.demo.service;


import com.example.demo.model.UserTokenState;

public interface LoginService {
    UserTokenState logIn(String username, String password);
}
