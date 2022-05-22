package com.example.demo.service.impl;

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public Boolean isPredefined(String username){
        Admin admin= adminRepository.findByUsername(username);
        return admin.getPredefined();
    }
    public List<Admin> getAllAdmin(){
        return adminRepository.findAll();
    }

    public Boolean hasAlreadyResetPassword(String username){
        Admin admin= adminRepository.findByUsername(username);
        return admin.getChangedPassword();
    }
}
