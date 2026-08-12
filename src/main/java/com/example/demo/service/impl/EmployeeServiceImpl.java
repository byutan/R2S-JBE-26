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
        Employee supervisor = fetchSupervisor(employeeRequest.getSupervisorId());
        Employee newEmployee = employeeRepository.save(new Employee(
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getBirthDate(),
                supervisor
        ));
        return getEmployeeResponse(newEmployee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() throws ResourceNotFoundException {
        return employeeRepository.findAll().stream().map(EmployeeServiceImpl::getEmployeeResponse).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse getEmployeeById(int id) {
        Employee employee = getEmployee(id);
        return getEmployeeResponse(employee);
    }


    @Override
    public EmployeeResponse updateEmployeeById(int id, EmployeeRequest employeeRequest) {
        Employee employeeToUpdate = getEmployee(id);
        Employee supervisor = fetchSupervisor(employeeRequest.getSupervisorId());
        employeeToUpdate.setFirst_name(employeeRequest.getFirstName());
        employeeToUpdate.setLast_name(employeeRequest.getLastName());
        employeeToUpdate.setBirth_date(employeeRequest.getBirthDate());
        employeeToUpdate.setSupervisor(supervisor);
        employeeRepository.save(employeeToUpdate);
        return getEmployeeResponse(employeeToUpdate);
    }


    @Override
    public void deleteEmployeeById(int id) {
        Employee employeeToDelete = getEmployee(id);
        employeeRepository.delete(employeeToDelete);
    }

    private static @NonNull EmployeeResponse getEmployeeResponse(Employee employee) {
        Integer supervisor_id = (employee.getSupervisor() != null)
                ? employee.getSupervisor().getEmployee_id()
                : null;

        return new EmployeeResponse(
                employee.getEmployee_id(),
                employee.getFirst_name(),
                employee.getLast_name(),
                employee.getBirth_date(),
                supervisor_id
        );
    }

    private @NonNull Employee getEmployee(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee with id " + id + " not found."
                ));
    }

    private Employee fetchSupervisor(Integer supervisor_id) {
        if (supervisor_id == null) {
            return null;
        }
        return employeeRepository.findById(supervisor_id)
                .orElseThrow(() -> new ResourceNotFoundException("Supervisor not found with id: " + supervisor_id));
    }
}
