package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Username is empty.")
    private String username;

    @NotBlank(message = "Password is empty.")
    private String password;
}
