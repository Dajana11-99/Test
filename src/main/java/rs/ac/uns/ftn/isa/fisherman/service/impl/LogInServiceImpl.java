package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.service.LoginService;
import rs.ac.uns.ftn.isa.fisherman.model.User;
import rs.ac.uns.ftn.isa.fisherman.model.UserTokenState;
import rs.ac.uns.ftn.isa.fisherman.repository.UserRepository;
import rs.ac.uns.ftn.isa.fisherman.security.TokenUtils;


@Service
public class LogInServiceImpl implements LoginService {

    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public LogInServiceImpl(TokenUtils tokenUtils, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;

    }
    public UserTokenState logIn(String username, String password) {
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
