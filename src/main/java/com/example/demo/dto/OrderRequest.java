package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class OrderRequest {
    @PastOrPresent
    @NotBlank
    private Date order_date;

    @NotBlank(message = "Customer id empty")
    private Integer customer_id;

    @NotBlank(message = "Employee id empty")
    private Integer employee_id;
}
