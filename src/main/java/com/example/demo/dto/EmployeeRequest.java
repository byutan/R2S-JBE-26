package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    @NotBlank(message = "First name is empty.")
    private String firstName;

    @NotBlank(message = "Last name is empty.")
    private String lastName;

    @NotNull(message = "Birthdate is empty.")
    @PastOrPresent
    private Date birthDate;

    private Integer supervisorId;
}
