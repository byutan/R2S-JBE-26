package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Integer order_id;

    @Column(name="order_date")
    private Date order_date = Date.valueOf(LocalDate.now());

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "customer_id")
    private Customer ordered_customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "employee_id")
    private Employee handled_employee;
}
