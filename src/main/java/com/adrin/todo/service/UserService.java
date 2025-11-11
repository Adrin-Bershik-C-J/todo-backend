package com.adrin.todo.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.adrin.todo.dto.request.RegisterRequestDto;
import com.adrin.todo.dto.response.RegisterResponseDto;
import com.adrin.todo.entity.User;
import com.adrin.todo.exception.UserAlreadyExistsException;
import com.adrin.todo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;    
    private final PasswordEncoder passwordEncoder;
    
    public RegisterResponseDto register(RegisterRequestDto request){

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("User already exists!");
        }        

        User newUser=new User();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(newUser);

        String message="User registered successfully!";
        return new RegisterResponseDto(message);
    }

    
}
