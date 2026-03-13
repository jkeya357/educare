package com.pm.userservice.service;

import com.pm.userservice.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {


    User authenticate(String email, String password);
    String generateToken(User user);
    UserDetails validateToken(String token);
}
