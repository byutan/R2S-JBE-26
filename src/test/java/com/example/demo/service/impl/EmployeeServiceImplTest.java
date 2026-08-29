package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private EmployeeRequest request;
    @BeforeEach
    public void setup() {
        employee = new Employee();
        employee.setEmployee_id(1);
        employee.setFirst_name("John");
        employee.setLast_name("Doe");
        employee.setBirth_date(Date.valueOf("2005-12-28"));
        employee.setSupervisor(null);

        request = new EmployeeRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setBirthDate(java.sql.Date.valueOf("2005-12-28"));
        request.setSupervisorId(null);
    }

    @Test
    void getAll_shouldReturnList() {
        when(employeeRepository.findAll()).thenReturn(List.of(employee, employee));
        List<EmployeeResponse> result = employeeService.getAllEmployees();
        assertEquals(2, result.size());
    }

    @Test
    void getById_shouldReturnEmployee() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        EmployeeResponse result = employeeService.getEmployeeById(1);
        assertEquals("John", result.getFirstName());
    }

    @Test
    void getById_withWrongId_shouldReturnResourceNotFoundException() {
        when(employeeRepository.findById(4)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.getEmployeeById(4));
        assertEquals("Employee with id 4 not found.", exception.getMessage());
    }

    @Test
    void createEmployee_shouldReturnEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeResponse result = employeeService.createEmployee(request);
        assertEquals("John", result.getFirstName());
    }

    @Test
    void updateEmployee_shouldReturnUpdatedEmployee() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeResponse result = employeeService.updateEmployeeById(1, request);
        assertEquals("John", result.getFirstName());
    }

    @Test
    void updateEmployee_withWrongId_shouldReturnUpdatedEmployee() {
        when(employeeRepository.findById(1)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.updateEmployeeById(1, request));
        assertEquals("Employee with id 1 not found.", exception.getMessage());
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void deleteEmployee_shouldDeleteEmployee() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        employeeService.deleteEmployeeById(1);
        verify(employeeRepository, times(1)).delete(employee);
    }

    @Test
    void deleteEmployee_withWrongId_shouldThrowException() {
        when(employeeRepository.findById(1)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.deleteEmployeeById(1);
        });
        assertEquals("Employee with id 1 not found.", exception.getMessage());
        verify(employeeRepository, never()).delete(any(Employee.class));
    }
}
