package com.example.demo.controller;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.sql.Date;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc()
public class CustomerControllerSecurityTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CustomerService customerService;

    @Test
    void createCustomer_shouldReturn201() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setFirstName("John");
        customerRequest.setLastName("Doe");
        customerRequest.setBirthDate(Date.valueOf("2005-12-28"));
        customerRequest.setEmailAddress("jdoe@email.com");
        customerRequest.setPhoneNumber("1234567890");
        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequest))
                .with(user("admin").authorities(new SimpleGrantedAuthority("CUSTOMER_CREATE"))))
                .andExpect(status().isCreated());
    }

    @Test
    void createCustomer_withoutToken_shouldReturn401() throws Exception {
        mockMvc.perform(post("/api/v1/customers"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void createCustomer_withoutPermission_shouldReturn403() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setFirstName("John");
        customerRequest.setLastName("Doe");
        customerRequest.setBirthDate(Date.valueOf("2005-12-28"));
        customerRequest.setEmailAddress("jdoe@email.com");
        customerRequest.setPhoneNumber("1234567890");
        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequest))
                .with(user("user").authorities(new SimpleGrantedAuthority("CUSTOMER_VIEW"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void getAllCustomers_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/customers")
                .with(user("admin").authorities(new SimpleGrantedAuthority("CUSTOMER_VIEW"))))
                .andExpect(status().isOk());
    }

    @Test
    void getAllCustomers_withoutToken_shouldReturn401() throws Exception {
        mockMvc.perform(get("/api/v1/customers"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getAllCustomers_withoutPermission_shouldReturn403() throws Exception {
        mockMvc.perform(get("/api/v1/customers")
                        .with(user("user").authorities(new SimpleGrantedAuthority("ORDER_VIEW"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void getCustomerByName_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/customers?name=John")
                .with(user("admin").authorities(new SimpleGrantedAuthority("CUSTOMER_VIEW"))))
                .andExpect(status().isOk());
    }

    @Test
    void getCustomerByName_withoutToken_shouldReturn401() throws Exception {
        mockMvc.perform(get("/api/v1/customers?name=John"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getCustomerByName_withoutPermission_shouldReturn403() throws Exception {
        mockMvc.perform(get("/api/v1/customers?name=John")
                .with(user("user").authorities(new SimpleGrantedAuthority("ORDER_VIEW"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void getCustomerById_withoutPermission_shouldReturn403() throws Exception {
        CustomerResponse response = new CustomerResponse();
        when(customerService.findCustomerById(1)).thenReturn(response);
        mockMvc.perform(get("/api/v1/customers/1")
                        .with(user("user").authorities(new SimpleGrantedAuthority("ORDER_VIEW"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void getCustomerById_withoutToken_shouldReturn401() throws Exception {
        CustomerResponse response = new CustomerResponse();
        when(customerService.findCustomerById(1)).thenReturn(response);
        mockMvc.perform(get("/api/v1/customers/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getCustomerById_shouldReturn200() throws Exception {
        CustomerResponse response = new CustomerResponse();
        when(customerService.findCustomerById(1)).thenReturn(response);
        mockMvc.perform(get("/api/v1/customers/1")
                .with(user("admin").authorities(new SimpleGrantedAuthority("CUSTOMER_VIEW"))))
                .andExpect(status().isOk());
    }

    @Test
    void updateCustomerById_shouldReturn200() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setFirstName("John");
        customerRequest.setLastName("Doe");
        customerRequest.setBirthDate(Date.valueOf("2005-12-28"));
        customerRequest.setEmailAddress("jdoe@email.com");
        customerRequest.setPhoneNumber("1234567890");
        CustomerResponse response = new CustomerResponse();
        when(customerService.updateCustomerById(1, customerRequest)).thenReturn(response);
        mockMvc.perform(put("/api/v1/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequest))
                        .with(user("admin").authorities(new SimpleGrantedAuthority("CUSTOMER_UPDATE"))))
                .andExpect(status().isOk());
    }

    @Test
    void updateCustomerById_withoutToken_shouldReturn401() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setFirstName("John");
        customerRequest.setLastName("Doe");
        customerRequest.setBirthDate(Date.valueOf("2005-12-28"));
        customerRequest.setEmailAddress("jdoe@email.com");
        customerRequest.setPhoneNumber("1234567890");
        CustomerResponse response = new CustomerResponse();
        when(customerService.updateCustomerById(1, customerRequest)).thenReturn(response);
        mockMvc.perform(put("/api/v1/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequest)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void updateCustomerById_withoutPermission_shouldReturn403() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setFirstName("John");
        customerRequest.setLastName("Doe");
        customerRequest.setBirthDate(Date.valueOf("2005-12-28"));
        customerRequest.setEmailAddress("jdoe@email.com");
        customerRequest.setPhoneNumber("1234567890");
        CustomerResponse response = new CustomerResponse();
        when(customerService.updateCustomerById(1, customerRequest)).thenReturn(response);
        mockMvc.perform(put("/api/v1/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequest))
                        .with(user("admin").authorities(new SimpleGrantedAuthority("CUSTOMER_VIEW"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteCustomerById_shouldReturn204() throws Exception {
        doNothing().when(customerService).deleteCustomerById(1);
        mockMvc.perform(delete("/api/v1/customers/1")
                    .with(user("admin").authorities(new SimpleGrantedAuthority("CUSTOMER_DELETE"))))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteCustomerById_withoutToken_shouldReturn401() throws Exception {
        doNothing().when(customerService).deleteCustomerById(1);
        mockMvc.perform(delete("/api/v1/customers/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteCustomerById_withoutPermission_shouldReturn401() throws Exception {
        doNothing().when(customerService).deleteCustomerById(1);
        mockMvc.perform(delete("/api/v1/customers/1")
                .with(user("admin").authorities(new SimpleGrantedAuthority("CUSTOMER_VIEW"))))
                .andExpect(status().isForbidden());
    }
}
