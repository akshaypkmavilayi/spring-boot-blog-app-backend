package com.blog.main.service;

import com.blog.main.dto.LoginRequest;
import com.blog.main.dto.RegisterRequest;
import com.blog.main.entities.UserDetailsEntity;
import com.blog.main.repositories.UserDetailsRepository;
import com.blog.main.security.JwtTockenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDetailsRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTockenProvider tokenProvider;

    @Override
    public void signup(RegisterRequest registerRequest) {
        UserDetailsEntity userDetails = new UserDetailsEntity();
        userDetails.setUserName(registerRequest.getUserName());
        userDetails.setPassword(encodePassword(registerRequest.getPassword()));
        userDetails.setEmailId(registerRequest.getEmailId());
        userRepo.save(userDetails);
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {

        /**
         * authManager.authenticate(...): This line initiates the authentication process using
         * the AuthenticationManager. It creates an Authentication object by attempting to
         * authenticate the provided username and password. It uses the UsernamePasswordAuthenticationToken
         * with the provided username and password from the loginRequest.
         *
         * ***/
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                loginRequest.getPassword()));
        String authenticationToken = tokenProvider.generateToken(authentication);

        /**
         * SecurityContextHolder.getContext().setAuthentication(authentication): Sets the authenticated
         * Authentication object in the security context using the SecurityContextHolder.
         * This ensures that the current user is authenticated within the security context for
         * the duration of the request.
         * ***/
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUserName());

    }

    @Override
    public Optional<User> getCurrentUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
