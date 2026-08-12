package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class CustomerRequest {
    @NotBlank(message = "First name is empty.")
    private String firstName;
    @NotBlank(message = "Last name is empty.")
    private String lastName;
    @NotNull(message = "Birthdate is empty.")
    @PastOrPresent
    private Date birthDate;
    @NotBlank(message = "Email address is empty.")
    @Email(message = "Invalid email address.")
    private String emailAddress;
    @Pattern(message = "Invalid phone number.", regexp = "^[0-9]{10}$")
    private String phoneNumber;
}
