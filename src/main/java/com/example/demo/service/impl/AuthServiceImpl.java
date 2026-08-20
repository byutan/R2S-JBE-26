package com.example.demo.service.impl;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.RegisterResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.BusinessConflictException;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtService;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        String email = registerRequest.getEmail();
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new BusinessConflictException("Username already exists.", null);
        } else if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new BusinessConflictException("Email already exists.", null);
        }

        Role role = roleRepository.findByName("USER");
        if (role == null) {
            throw new ResourceNotFoundException("Default role USER not found");
        }

        String reqPassword = registerRequest.getPassword();
        String hashPassword = passwordEncoder.encode(reqPassword);
        userRepository.save(new User(username, email, hashPassword, Set.of(role)));
        return RegisterResponse.builder()
                .message("Register successfully")
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new BusinessValidationException("Invalid username or password", HttpStatus.UNPROCESSABLE_CONTENT, null));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BusinessValidationException("Invalid username or password", HttpStatus.UNPROCESSABLE_CONTENT, null);
        }

        String token = jwtService.generateToken(user);
        long exp = jwtService.getExpirationSeconds(token);
        return LoginResponse.builder()
                .token(token)
                .expiresAtEpochSeconds(exp)
                .build();
    }
}
