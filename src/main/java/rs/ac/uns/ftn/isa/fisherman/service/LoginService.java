package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.UserTokenState;

public interface LoginService {
    UserTokenState logIn(String username, String password);
}
