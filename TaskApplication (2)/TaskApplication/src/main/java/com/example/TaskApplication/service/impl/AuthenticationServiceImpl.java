package com.example.TaskApplication.service.impl;

import com.example.TaskApplication.dto.response.JwtAuthenticationResponse;
import com.example.TaskApplication.dto.request.LoginRequest;
import com.example.TaskApplication.dto.request.RegisterRequest;
import com.example.TaskApplication.dto.response.RegistrationResponse;
import com.example.TaskApplication.model.Role;
import com.example.TaskApplication.model.User;
import com.example.TaskApplication.repository.UserRepository;


import com.example.TaskApplication.service.AuthenticationService;
import com.example.TaskApplication.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public RegistrationResponse register(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        return new RegistrationResponse(
                "User registered successfully",
                user.getUsername(),
                user.getRole().name()
        );
    }

    public JwtAuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword())
        );

        var user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        var jwt = jwtService.generateToken((UserDetails) user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        return jwtAuthenticationResponse;
    }

}
