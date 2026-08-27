package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class OrderRequest {
    @PastOrPresent
    private Date order_date;

    @NotNull(message = "Customer id empty")
    private Integer customer_id;

    @NotNull(message = "Employee id empty")
    private Integer employee_id;
}
