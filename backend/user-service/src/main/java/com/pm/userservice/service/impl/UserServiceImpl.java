package com.pm.userservice.service.impl;

import com.pm.userservice.exceptions.EmailOrUsernameExistsException;
import com.pm.userservice.exceptions.UserExistsException;
import com.pm.userservice.exceptions.UserNotFoundException;
import com.pm.userservice.model.dto.CreateUserRequestDto;
import com.pm.userservice.model.entity.User;
import com.pm.userservice.model.utils.Role;
import com.pm.userservice.repository.UserRepository;
import com.pm.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User createUser(CreateUserRequestDto request) {

        if(userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserExistsException("User already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setRole(request.getRole() == null ? Role.STUDENT : request.getRole() );
        user.setCreatedDate(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
