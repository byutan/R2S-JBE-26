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
public class OrderRequest {
    private Date order_date;

    @NotNull(message = "Customer id empty")
    private Integer customer_id;

    @NotNull(message = "Employee id empty")
    private Integer employee_id;
}
