package com.pm.userservice.controller;

import com.pm.userservice.model.dto.UserResponseDto;
import com.pm.userservice.model.entity.User;
import com.pm.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> userDto = userService.findAll();
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/id")
    public ResponseEntity<UserResponseDto> findUser(@RequestParam UUID userId){
        UserResponseDto responseDto = userService.findUserById(userId);
        return ResponseEntity.ok(responseDto);
    }
}
