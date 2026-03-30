package com.pm.userservice.service.impl;

import com.pm.userservice.model.entity.User;
import com.pm.userservice.service.AuthService;
import com.pm.userservice.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    private final  String secretKey = "5367BCBABCD98FQ98PVN0@$&*@&70639792F423F8284D";

    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            UserService userService
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }
    private final Long jwtExpiryMs = 600000L;
    private final Long jwtRefreshMs = 604800000L;

    @Override
    public void generateRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        response.setHeader("Set-Cookie",
                "refreshToken=" + refreshToken +
                        "; Path=/; HttpOnly; Max-Age=" + (7 * 24 * 60 * 60) +
                        "; SameSite=None; Secure");

    }

    @Override
    public String extractRefreshTokenCookie(HttpServletRequest request) {

        if(request.getCookies() == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("refreshToken")) {
                return cookie.getValue();
            }
        }
        return null;
    }

    @Override
    public User authenticate(String email, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        return userService.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    @Override
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(user.getEmail())
                .signWith(getSecretKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiryMs))
                .compact();
    }

    @Override
    public String generateRefreshToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claim("refreshToken", claims)
                .subject(user.getEmail())
                .signWith(getSecretKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtRefreshMs))
                .compact();
    }

    @Override
    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    @Override
    public boolean isRefreshTokenValid(String refreshToken) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(refreshToken)
                    .getPayload();
            return "refreshToken".equals(claims.get("type")) &&
                    !claims.getExpiration().before(new Date());
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public UserDetails validateToken(String token) {
        String username = extractUsername(token);
        return userDetailsService.loadUserByUsername(username);
    }

    private String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();

    }

    private SecretKey getSecretKey(){
       byte[] accessKey =  secretKey.getBytes();
       return Keys.hmacShaKeyFor(accessKey);
    }
}
