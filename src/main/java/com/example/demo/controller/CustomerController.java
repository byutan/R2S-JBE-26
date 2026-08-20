package com.example.demo.controller;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    // CREATE
    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('CUSTOMER_CREATE')")
    public ResponseEntity<CustomerResponse> addCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customerRequest));
    }
    // READ BY ID
    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PreAuthorize("hasAuthority('CUSTOMER_VIEW')")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomerById(id));
    }

    // READ ALL
    @GetMapping
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PreAuthorize("hasAuthority('CUSTOMER_VIEW')")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllCustomers());
    }

    // READ LIKE
    @GetMapping("/search")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PreAuthorize("hasAuthority('CUSTOMER_VIEW')")
    public ResponseEntity<List<CustomerResponse>> getAllCustomersByName(@RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllCustomersByName(name));
    }

    // UPDATE
    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('CUSTOMER_UPDATE')")
    public ResponseEntity<CustomerResponse> updateCustomerById(@PathVariable Integer id, @Valid @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomerById(id, customerRequest));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('CUSTOMER_DELETE')")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Integer id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
