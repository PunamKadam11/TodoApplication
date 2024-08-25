package com.example.TaskApplication.service.impl;

import com.example.TaskApplication.exceptions.JwtException;
import com.example.TaskApplication.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private static final String SECRET_KEY = "QmFzZTY0IGVuY29kaW5nIHNjaGVtZXMgYXJlIHVzZWQgd2hlbiB0aGVyZSBpcyBhIG5lZWQgdG8gZW5jb2RlIGJpbmFyeSBkYXRhIGluIGEgc2VjdXJlIGZvcm1hdC4gVGhpcyBlbmNvZGluZyBmb3JtYXQgaXMgdXNlZCBjb21tb25seSBpbiBhIG51bWJlciBvZiBhcHBsaWNhdGlvbnMgaW4gdGhlIGZpZWxkIG9mIGFjY291bnRzIGluIGJhc2U2NC4=";

    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String extractUserName(String token) throws JwtException {
        try {
            return extractClaims(token, Claims::getSubject);
        } catch (JwtException e) {
            throw new JwtException("Error extracting username from token", e);
        }
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) throws JwtException {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) throws JwtException {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new JwtException("Token has expired", e);
        } catch (Exception e) {
            throw new JwtException("Invalid JWT token", e);
        }
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    @Override
    public Date getExpirationDateFromToken(String token) throws JwtException {
        try {
            return extractClaims(token, Claims::getExpiration);
        } catch (JwtException e) {
            throw new JwtException("Error extracting expiration date from token", e);
        }
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) throws JwtException {
        try {
            final String username = extractUserName(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (ExpiredJwtException e) {
            throw new JwtException("Token has expired", e);
        } catch (SignatureException e) {
            throw new JwtException("Invalid JWT signature", e);
        } catch (Exception e) {
            throw new JwtException("Error validating token", e);
        }
    }
}
