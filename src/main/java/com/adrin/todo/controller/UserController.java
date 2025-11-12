package com.adrin.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrin.todo.dto.request.LoginRequestDto;
import com.adrin.todo.dto.request.RegisterRequestDto;
import com.adrin.todo.dto.response.LoginResponseDto;
import com.adrin.todo.dto.response.RegisterResponseDto;
import com.adrin.todo.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto>register(@Valid @RequestBody RegisterRequestDto request){
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto>login(@Valid @RequestBody LoginRequestDto request){
        return ResponseEntity.ok(userService.login(request));
    }
}
