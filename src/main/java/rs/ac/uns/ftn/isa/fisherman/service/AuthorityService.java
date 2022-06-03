package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.Authority;
import java.util.List;


public interface AuthorityService {
    List<Authority> findById(Long id);
    List<Authority> findByname(String name);
}