package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        validateEmployeeRequest(employeeRequest);
        Employee newEmployee = employeeRepository.save(new Employee(
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getBirthDate(),
                employeeRequest.getSupervisorId()
        ));
        return getEmployeeResponse(newEmployee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() throws ResourceNotFoundException {
        List<EmployeeResponse> employeeResponseList;
        List<Employee> employees = employeeRepository.findAll();
        employeeResponseList = employees.stream().map(EmployeeServiceImpl::getEmployeeResponse).collect(Collectors.toList());
        return employeeResponseList;
    }

    @Override
    public EmployeeResponse getEmployeeById(int id) {
        Employee employee = getEmployee(id);
        return getEmployeeResponse(employee);
    }


    @Override
    public EmployeeResponse updateEmployeeById(int id, EmployeeRequest employeeRequest) {
        Employee employeeToUpdate = getEmployee(id);
        validateEmployeeRequest(employeeRequest);
        employeeToUpdate.setFirst_name(employeeRequest.getFirstName());
        employeeToUpdate.setLast_name(employeeRequest.getLastName());
        employeeToUpdate.setBirth_date(employeeRequest.getBirthDate());
        employeeRepository.save(employeeToUpdate);
        return getEmployeeResponse(employeeToUpdate);
    }


    @Override
    public void deleteEmployeeById(int id) {
        Employee employeeToDelete = getEmployee(id);
        employeeRepository.delete(employeeToDelete);
    }

    private static @NonNull EmployeeResponse getEmployeeResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getEmployee_id(),
                employee.getFirst_name(),
                employee.getLast_name(),
                employee.getBirth_date(),
                employee.getSupervisor_id()
        );
    }

    private @NonNull Employee getEmployee(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee with id " + id + " not found."
                ));
    }

    private void validateEmployeeRequest(EmployeeRequest employeeRequest) {
        if(employeeRequest.getFirstName().trim().isEmpty()
                || employeeRequest.getLastName().trim().isEmpty()
                || employeeRequest.getBirthDate() == null) {
            throw new IllegalArgumentException("Invalid request input.");
        }
        if (employeeRequest.getSupervisorId() != null && employeeRepository.findById(employeeRequest.getSupervisorId()).isEmpty()) {
            throw new ResourceNotFoundException("Supervisor with id " + employeeRequest.getSupervisorId() + " not found.");
        }
    }
}
