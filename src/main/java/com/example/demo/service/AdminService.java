package com.example.demo.service;

import com.example.demo.model.Admin;

import java.util.List;

public interface AdminService {
    Admin findByUsername(String username);
    Boolean isPredefined(String username);
    List<Admin> getAllAdmin();
    Boolean hasAlreadyResetPassword(String username);
}
