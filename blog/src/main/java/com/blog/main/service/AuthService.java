package com.blog.main.service;

import com.blog.main.dto.LoginRequest;
import com.blog.main.dto.RegisterRequest;

import java.util.Optional;

public interface AuthService {
    void signup(RegisterRequest registerRequest);

    AuthenticationResponse login(LoginRequest loginRequest);

    <T> Optional<T> getCurrentUser();
}
