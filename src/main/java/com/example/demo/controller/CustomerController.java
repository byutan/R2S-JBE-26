package com.example.demo.controller;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    // CREATE
    @PostMapping
    public ResponseEntity<CustomerResponse> addCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customerRequest));
    }
    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomerById(id));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllCustomers());
    }

    // READ LIKE
    @GetMapping("/search")
    public ResponseEntity<List<CustomerResponse>> getAllCustomersByName(@RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllCustomersByName(name));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomerById(@PathVariable Integer id, @Valid @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomerById(id, customerRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponse> deleteCustomerById(@PathVariable Integer id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
