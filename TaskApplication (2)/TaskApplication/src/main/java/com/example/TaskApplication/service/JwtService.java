package com.example.TaskApplication.service;

import com.example.TaskApplication.exceptions.JwtException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    // Get expiration date from token
    Date getExpirationDateFromToken(String token);

    Boolean validateToken(String token, UserDetails userDetails) throws JwtException;

}
