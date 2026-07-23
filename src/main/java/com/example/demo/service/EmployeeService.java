package com.example.demo.service;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse getEmployeeById(int id);
    EmployeeResponse updateEmployeeById(int id, EmployeeRequest employeeRequest);
    void deleteEmployeeById(int id);
}
