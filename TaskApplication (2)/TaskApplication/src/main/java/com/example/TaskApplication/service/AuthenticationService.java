package com.example.TaskApplication.service;


import com.example.TaskApplication.dto.response.JwtAuthenticationResponse;
import com.example.TaskApplication.dto.request.LoginRequest;
import com.example.TaskApplication.dto.request.RegisterRequest;
import com.example.TaskApplication.dto.response.RegistrationResponse;

public interface AuthenticationService {
    RegistrationResponse register(RegisterRequest registerRequest);
    public JwtAuthenticationResponse login(LoginRequest loginRequest);
}
