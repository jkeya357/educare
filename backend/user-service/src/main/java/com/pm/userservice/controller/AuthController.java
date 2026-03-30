package com.pm.userservice.controller;

import com.pm.userservice.model.dto.AuthResponseDto;
import com.pm.userservice.model.dto.CreateUserRequestDto;
import com.pm.userservice.model.dto.LoginUserRequestDto;
import com.pm.userservice.model.entity.User;
import com.pm.userservice.repository.UserRepository;
import com.pm.userservice.service.AuthService;
import com.pm.userservice.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private final UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDto> signIn(
            @RequestBody LoginUserRequestDto loginUserRequestDto,
            HttpServletResponse response
    ) {

        User user = authService.authenticate(loginUserRequestDto.getEmail(), loginUserRequestDto.getPassword());
        String token = authService.generateToken(user);
        String refreshToken = authService.generateRefreshToken(user);
        authService.generateRefreshTokenCookie(response, refreshToken);
        AuthResponseDto dto =  AuthResponseDto.builder()
                .userId(user.getUserId())
                .token(token)
                .username(user.getUsername())
                .role(user.getRole())
                .duration(600000L)
                .build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signUp(
            @RequestBody CreateUserRequestDto createUserRequestDto,
            HttpServletResponse response
    ){

        User createUser = userService.createUser(createUserRequestDto);
        String token = authService.generateToken(createUser);
        String refreshToken = authService.generateRefreshToken(createUser);
        authService.generateRefreshTokenCookie(response, refreshToken);
        AuthResponseDto dto = AuthResponseDto.builder()
                .userId(createUser.getUserId())
                .token(token)
                .username(createUser.getUsername())
                .role(createUser.getRole())
                .duration(600000L)
                .build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refresh(HttpServletRequest request){

        String refreshToken = authService.extractRefreshTokenCookie(request);

        if(refreshToken == null && !authService.isRefreshTokenValid(refreshToken)){
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }

        String email = authService.extractEmail(refreshToken);

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );

        String newToken = authService.generateToken(user);

        AuthResponseDto res = AuthResponseDto.builder()
                .userId(user.getUserId())
                .token(newToken)
                .username(user.getUsername())
                .role(user.getRole())
                .duration(600000L)
                .build();
        return ResponseEntity.ok(res);

    }
}
