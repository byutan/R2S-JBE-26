package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name="Employees")
public class Employee {
    @Id
    @Column(name="employee_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer employee_id;
    @Column(name="first_name")
    private String first_name;
    @Column(name="last_name")
    private String last_name;
    @Column(name="birth_date")
    private Date birth_date;
    @Column(name="supervisor_id")
    private Integer supervisor_id;

    public Employee(String first_name, String last_name, Date birth_date, Integer supervisor_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.supervisor_id = supervisor_id;
    }
}
