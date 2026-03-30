package com.pm.userservice.service;

import com.pm.userservice.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {


    void generateRefreshTokenCookie(HttpServletResponse response, String refreshToken);
    String extractRefreshTokenCookie(HttpServletRequest request);
    User authenticate(String email, String password);
    String generateToken(User user);
    String generateRefreshToken(User user);
    String extractEmail(String token);
    boolean isRefreshTokenValid(String refreshToken);
    UserDetails validateToken(String token);
}
