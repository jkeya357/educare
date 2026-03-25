package com.pm.userservice.service;

import com.pm.userservice.model.dto.CreateUserRequestDto;
import com.pm.userservice.model.entity.User;
import com.pm.userservice.model.dto.UserResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<UserResponseDto> findAll();
    UserResponseDto findUserById(UUID id);
    User createUser(CreateUserRequestDto request);
    Optional<User> findByEmail(String email);
}
