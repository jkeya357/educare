package com.pm.userservice.service;

import com.pm.userservice.model.dto.CreateUserRequestDto;
import com.pm.userservice.model.entity.User;

import java.util.Optional;

public interface UserService {


    User createUser(CreateUserRequestDto request);
    Optional<User> findByEmail(String email);
}
