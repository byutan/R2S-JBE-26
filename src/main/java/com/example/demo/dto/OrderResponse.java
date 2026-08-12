package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
public class OrderResponse {
    private Integer orderId;
    private Date order_date;
    private Integer customer_id;
    private Integer employee_id;
}
