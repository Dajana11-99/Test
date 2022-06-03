package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.Admin;

import java.util.List;

public interface AdminService {
    Admin findByUsername(String username);
    Boolean isPredefined(String username);
    List<Admin> getAllAdmin();
    Boolean hasAlreadyResetPassword(String username);
}
