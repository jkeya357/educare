package com.pm.userservice.controller;

import com.pm.userservice.model.dto.AuthResponseDto;
import com.pm.userservice.model.dto.CreateUserRequestDto;
import com.pm.userservice.model.dto.LoginUserRequestDto;
import com.pm.userservice.model.entity.User;
import com.pm.userservice.service.AuthService;
import com.pm.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDto> signIn(@RequestBody LoginUserRequestDto loginUserRequestDto) {

        User user = authService.authenticate(loginUserRequestDto.getEmail(), loginUserRequestDto.getPassword());
        String token = authService.generateToken(user);
        AuthResponseDto dto =  AuthResponseDto.builder()
                .userId(user.getUserId())
                .token(token)
                .duration(86400)
                .build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signUp(
            @RequestBody CreateUserRequestDto createUserRequestDto
    ){

        User createUser = userService.createUser(createUserRequestDto);
        String token = authService.generateToken(createUser);
        AuthResponseDto dto = AuthResponseDto.builder()
                .userId(createUser.getUserId())
                .token(token)
                .duration(86400)
                .build();
        return ResponseEntity.ok(dto);
    }
}
