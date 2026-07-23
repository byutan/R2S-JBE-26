package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;


@Data
@AllArgsConstructor
public class EmployeeResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Integer supervisorId;
}
