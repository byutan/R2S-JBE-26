package com.example.demo.service.impl;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.entity.Customer;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    private Customer customer;

    private CustomerRequest request;

    @BeforeEach
    public void setup() {
        customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmailAddress("jdoe@email.com");
        customer.setPhoneNumber("0931311482");

        request = new CustomerRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmailAddress("jdoe@email.com");
        request.setPhoneNumber("0931311482");
    }

    @Test
    void getAllCustomers_shouldReturnListOfCustomers() {
        when(customerRepository.findAll()).thenReturn(List.of(customer, customer));
        List<CustomerResponse> result = customerServiceImpl.findAllCustomers();
        assertEquals(2, result.size());
    }

    @Test
    void getAllCustomers_shouldReturnEmptyList() {
        when(customerRepository.findAll()).thenReturn(List.of());
        List<CustomerResponse> result = customerServiceImpl.findAllCustomers();
        assertEquals(0, result.size());
    }

    @Test
    void getCustomerById_shouldReturnCustomer() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        CustomerResponse result = customerServiceImpl.findCustomerById(1);
        assertEquals("John", result.getFirstName());
    }

    @Test
    void getCustomerById_withWrongId_shouldThrowException() {
        when(customerRepository.findById(1)).thenReturn(Optional.empty());
        Exception result = assertThrows(ResourceNotFoundException.class, () -> customerServiceImpl.findCustomerById(1));
        assertEquals("Customer with id 1 not found.", result.getMessage());
    }

    @Test
    void getCustomerByName_shouldReturnList() {
        when(customerRepository.findAllCustomerLikeName("John")).thenReturn(List.of(customer, customer));
        List<CustomerResponse> result = customerServiceImpl.findAllCustomersByName("John");
        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("John", result.getFirst().getFirstName()),
                () -> assertEquals("John", result.get(1).getFirstName())
        );
    }

    @Test
    void createCustomer_shouldReturnCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        CustomerResponse result = customerServiceImpl.addCustomer(request);
        assertEquals("John", result.getFirstName());
    }

    @Test
    void updateCustomer_shouldReturnCustomer() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        CustomerResponse result = customerServiceImpl.updateCustomerById(1, request);
        assertEquals("John", result.getFirstName());
    }

    @Test
    void updateCustomer_withWrongId_shouldThrowException() {
        when(customerRepository.findById(1)).thenReturn(Optional.empty());
        Exception result = assertThrows(ResourceNotFoundException.class, () -> customerServiceImpl.updateCustomerById(1, request));
        assertEquals("Customer with id 1 not found.", result.getMessage());
    }

    @Test
    void deleteCustomer_shouldDeleteOnce() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        customerServiceImpl.deleteCustomerById(1);
        verify(customerRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteCustomer_withWrongId_shouldThrowException() {
        when(customerRepository.findById(1)).thenReturn(Optional.empty());
        Exception result = assertThrows(ResourceNotFoundException.class, () -> customerServiceImpl.deleteCustomerById(1));
        assertEquals("Customer with id 1 not found.", result.getMessage());
    }
}
