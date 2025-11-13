package com.adrin.todo.service;

import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;


import com.adrin.todo.dto.request.LoginRequestDto;
import com.adrin.todo.dto.request.RegisterRequestDto;
import com.adrin.todo.dto.response.LoginResponseDto;
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
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    
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

    public LoginResponseDto login(LoginRequestDto request){

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            String jwtToken = jwtService.generateToken(userDetails);

            return new LoginResponseDto(jwtToken);
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Invalid email or password");
        }           
    }   
    
    //profile update
}
