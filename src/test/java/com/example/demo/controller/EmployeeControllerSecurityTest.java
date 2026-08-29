package com.example.demo.controller;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc()
public class EmployeeControllerSecurityTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    EmployeeService employeeService;

    @Test
    void getAll_withoutToken_shouldReturn401() throws Exception {
        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getAll_withoutPermission_shouldReturn403() throws Exception {
        mockMvc.perform(get("/api/v1/employees")
                        .with(user("user").authorities(new SimpleGrantedAuthority("ORDER_VIEW"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void getAll_withPermission_shouldReturn200() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/employees")
                        .with(user("admin").authorities(new SimpleGrantedAuthority("EMPLOYEE_VIEW"))))
                .andExpect(status().isOk());
    }

    @Test
    void createEmployee_withoutPermission_shouldReturn403() throws Exception {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setBirthDate(java.sql.Date.valueOf("2000-01-01"));
        request.setSupervisorId(null);
        mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(user("user").authorities(new SimpleGrantedAuthority("EMPLOYEE_VIEW"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void createEmployee_withPermission_shouldReturn201() throws Exception {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setBirthDate(java.sql.Date.valueOf("2000-01-01"));
        request.setSupervisorId(null);
        mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(user("admin").authorities(new SimpleGrantedAuthority("EMPLOYEE_CREATE"))))
                .andExpect(status().isCreated());
    }

    @Test
    void createEmployee_withoutToken_shouldReturn401() throws Exception {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setBirthDate(java.sql.Date.valueOf("2000-01-01"));
        request.setSupervisorId(null);
        mockMvc.perform(post("/api/v1/employees"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void updateEmployee_withoutToken_shouldReturn401() throws Exception {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setBirthDate(java.sql.Date.valueOf("2000-12-01"));
        request.setSupervisorId(null);

        mockMvc.perform(put("/api/v1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void updateEmployee_withoutPermission_shouldReturn403() throws Exception {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setBirthDate(java.sql.Date.valueOf("2000-12-01"));
        request.setSupervisorId(null);

        mockMvc.perform(put("/api/v1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(user("user").authorities(new SimpleGrantedAuthority("EMPLOYEE_VIEW"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateEmployee_withPermission_shouldReturn200() throws Exception {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setBirthDate(java.sql.Date.valueOf("2000-12-01"));
        request.setSupervisorId(null);

        EmployeeResponse mockResponse = new EmployeeResponse();
        when(employeeService.updateEmployeeById(eq(1), any(EmployeeRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(put("/api/v1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(user("admin").authorities(new SimpleGrantedAuthority("EMPLOYEE_UPDATE"))))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEmployee_withoutToken_shouldReturn401() throws Exception {
        doNothing().when(employeeService).deleteEmployeeById(1);
        mockMvc.perform(delete("/api/v1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteEmployee_withoutPermission_shouldReturn403() throws Exception {
        doNothing().when(employeeService).deleteEmployeeById(1);

        mockMvc.perform(delete("/api/v1/employees/1")
                        .with(user("user").authorities(new SimpleGrantedAuthority("CUSTOMER_VIEW"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteEmployee_withPermission_shouldReturn204() throws Exception {
        doNothing().when(employeeService).deleteEmployeeById(1);

        mockMvc.perform(delete("/api/v1/employees/1")
                        .with(user("admin").authorities(new SimpleGrantedAuthority("EMPLOYEE_DELETE"))))
                .andExpect(status().isNoContent());
    }
}