package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id", referencedColumnName = "employee_id")
    private Employee supervisor;

    @OneToMany(mappedBy = "handled_employee")
    private List<Order> orders;

    public Employee(String first_name, String last_name, Date birth_date, Employee supervisor) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.supervisor = supervisor;
    }
}
