package com.example.demo.service.impl;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.entity.Customer;
import com.example.demo.exception.BusinessConflictException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) throws BusinessConflictException {
        if(customerRepository.findByEmailAddress(customerRequest.getEmailAddress()).isPresent()) {
            Map<String, String> fieldError = new HashMap<>();
            fieldError.put("emailAddress: ", customerRequest.getEmailAddress());
            throw new BusinessConflictException("Email address already exists.", fieldError);
        }
        Customer newCustomer = customerRepository.save(new Customer(
                customerRequest.getFirstName(),
                customerRequest.getLastName(),
                customerRequest.getBirthDate(),
                customerRequest.getEmailAddress(),
                customerRequest.getPhoneNumber()));

        return getCustomerResponse(newCustomer);
    }

    @Override
    public CustomerResponse findCustomerById(Integer id) throws ResourceNotFoundException {
        if(customerRepository.findById(id).isPresent()) {
            return getCustomerResponse(customerRepository.findById(id).get());
        }
        throw new ResourceNotFoundException("Customer with id " + id + " not found.");
    }

    @Override
    public CustomerResponse updateCustomerById(Integer id, CustomerRequest customerRequest) throws BusinessConflictException, ResourceNotFoundException {
        isEmployeeExisted(id);
        Customer customerToUpdate = customerRepository.findById(id).get();
        customerToUpdate.setFirstName(customerRequest.getFirstName());
        customerToUpdate.setLastName(customerRequest.getLastName());
        customerToUpdate.setBirthDate(customerRequest.getBirthDate());
        customerToUpdate.setEmailAddress(customerRequest.getEmailAddress());
        customerToUpdate.setPhoneNumber(customerRequest.getPhoneNumber());
        return getCustomerResponse(customerRepository.save(customerToUpdate));
    }

    @Override
    public void deleteCustomerById(Integer id) throws ResourceNotFoundException {
        isEmployeeExisted(id);
        customerRepository.deleteById(id);
    }

    private void isEmployeeExisted(Integer id) {
        if(customerRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Customer with id " + id + " not found.");
        }
    }


    @Override
    public List<CustomerResponse> findAllCustomers() throws ResourceNotFoundException {
        List<CustomerResponse> customerResponseList;
        List<Customer> allCustomers = customerRepository.findAll();
        customerResponseList = allCustomers.stream().map(CustomerServiceImpl::getCustomerResponse).toList();
        return customerResponseList;
    }

    @Override
    public List<CustomerResponse> findAllCustomersByName(String name) throws ResourceNotFoundException {
        List<CustomerResponse> customerResponseList;
        List<Customer> allCustomersWithSameName = customerRepository.findAllCustomerLikeName(name);
        customerResponseList = allCustomersWithSameName.stream().map(CustomerServiceImpl::getCustomerResponse).toList();
        return customerResponseList;
    }

    private static CustomerResponse getCustomerResponse(Customer newCustomer) {
        return CustomerResponse.builder()
                .customerId(newCustomer.getCustomerId())
                .firstName(newCustomer.getFirstName())
                .lastName(newCustomer.getLastName())
                .birthDate(newCustomer.getBirthDate())
                .emailAddress(newCustomer.getEmailAddress())
                .phoneNumber(newCustomer.getPhoneNumber())
                .build();
    }
}
