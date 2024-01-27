package com.blog.main.service;

import com.blog.main.entities.UserDetailsEntity;
import com.blog.main.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetailsEntity userDetails = userRepo.findByUserName(s).orElseThrow(() -> new UsernameNotFoundException("No user available" + s));
        return new User(userDetails.getUserName(),userDetails.getPassword(),true,true,true,true,getAuthorities("ROLE_USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String roleUser) {

        return Collections.singletonList(new SimpleGrantedAuthority(roleUser));
    }
}
