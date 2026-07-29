package com.example.demo.service;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.exception.BusinessConflictException;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerResponse addCustomer(CustomerRequest customerRequest) throws BusinessConflictException;
    CustomerResponse findCustomerById(Integer id) throws ResourceNotFoundException;
    CustomerResponse updateCustomerById(Integer id, CustomerRequest customerRequest) throws BusinessConflictException;
    void deleteCustomerById(Integer id) throws ResourceNotFoundException;
    List<CustomerResponse> findAllCustomers() throws ResourceNotFoundException;
    List<CustomerResponse> findAllCustomersByName(String name) throws ResourceNotFoundException;
}
