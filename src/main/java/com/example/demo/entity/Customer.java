package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name= "Customers")
public class Customer {
    @Id
    @Column(name="customer_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer customerId;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="birth_date", nullable = false)
    private Date birthDate;

    @Column(name="email_address", nullable = false, unique = true)
    private String emailAddress;

    @Column(name="phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "ordered_customer")
    private List<Order> orders;

    public Customer(String firstName, String lastName, Date birthDate, String emailAddress, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }
}
