package com.example.demo.controller;

import com.example.demo.dto.CustomerResponse;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;
import com.example.demo.service.CustomerService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
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
import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc()
public class OrderControllerSecurityTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private OrderService orderService;

    @MockitoBean
    private EmployeeService employeeService;

    @MockitoBean
    private CustomerService customerService;

    private CustomerResponse customerResponse;
    private EmployeeResponse employeeResponse;
    private OrderResponse orderResponse;
    @BeforeEach
    void setUp() {
        Employee employee = new Employee();
        employee.setEmployee_id(1);
        employee.setFirst_name("John");
        employee.setLast_name("Doe");
        employee.setBirth_date(Date.valueOf(LocalDate.now()));
        employee.setSupervisor(null);

        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("Johny");
        customer.setLastName("Does");
        customer.setBirthDate(Date.valueOf(LocalDate.now()));
        customer.setPhoneNumber("0931311482");
        customer.setEmailAddress("abc@email.xyz");

        customerResponse = new CustomerResponse();
        customerResponse.setFirstName("Johny");
        customerResponse.setLastName("Does");
        customerResponse.setBirthDate(Date.valueOf(LocalDate.now()));
        customerResponse.setPhoneNumber("0931311482");
        customerResponse.setEmailAddress("abc@email.xyz");

        employeeResponse = new EmployeeResponse();
        employeeResponse.setFirstName("John");
        employeeResponse.setLastName("Doe");
        employeeResponse.setBirthDate(Date.valueOf(LocalDate.now()));
        employeeResponse.setSupervisorId(null);

        orderResponse = new OrderResponse(1,Date.valueOf(LocalDate.now()), 1,1);
    }
    @Test
    void getAllOrders_withoutToken_shouldReturn401() throws Exception {
        mockMvc.perform(get("/api/v1/orders"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getAllOrders_withoutPermission_shouldReturn403() throws Exception {
        mockMvc.perform(get("/api/v1/orders")
                .with(user("user").authorities(new SimpleGrantedAuthority("CUSTOMER_VIEW"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void getAllOrders_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/orders")
                .with(user("user").authorities(new SimpleGrantedAuthority("ORDER_VIEW"))))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderById_withoutToken_shouldReturn401() throws Exception {
        mockMvc.perform(get("/api/v1/orders/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getOrderById_withoutPermission_shouldReturn403() throws Exception {
        mockMvc.perform(get("/api/v1/orders/1")
                        .with(user("user").authorities(new SimpleGrantedAuthority("CUSTOMER_VIEW"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void getOrderById_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/orders/1")
                .with(user("user").authorities(new SimpleGrantedAuthority("ORDER_VIEW"))))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderByEmployeeId_withoutToken_shouldReturn401() throws Exception {
        mockMvc.perform(get("/api/v1/orders/by-employee/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getOrderByEmployeeId_withoutPermission_shouldReturn403() throws Exception {
        mockMvc.perform(get("/api/v1/orders/by-employee/1")
                .with(user("user").authorities(new SimpleGrantedAuthority("ORDER_VIEW"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void getOrderByEmployeeId_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/orders/by-employee/1")
                .with(user("user").authorities(new SimpleGrantedAuthority("ORDER_ADMIN_VIEW"))))
                .andExpect(status().isOk());
    }

    @Test
    void createOrder_withoutToken_shouldReturn401() throws Exception {
        mockMvc.perform(post("/api/v1/orders"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void createOrder_withoutPermission_shouldReturn403() throws Exception {
        when(customerService.findCustomerById(1)).thenReturn(customerResponse);
        when(employeeService.getEmployeeById(1)).thenReturn(employeeResponse);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setEmployee_id(1);
        orderRequest.setCustomer_id(1);

        mockMvc.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest))
                        .with(user("user").authorities(new SimpleGrantedAuthority("ORDER_VIEW"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void createOrder_shouldReturn201() throws Exception {
        when(customerService.findCustomerById(1)).thenReturn(customerResponse);
        when(employeeService.getEmployeeById(1)).thenReturn(employeeResponse);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setEmployee_id(1);
        orderRequest.setCustomer_id(1);

        mockMvc.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest))
                        .with(user("user").authorities(new SimpleGrantedAuthority("ORDER_CREATE"))))
                .andExpect(status().isCreated());
    }

    @Test
    void updateOrder_withoutToken_shouldReturn401() throws Exception {
        when(customerService.findCustomerById(1)).thenReturn(customerResponse);
        when(employeeService.getEmployeeById(1)).thenReturn(employeeResponse);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setEmployee_id(1);
        orderRequest.setCustomer_id(1);

        mockMvc.perform(put("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void updateOrder_withoutPermission_shouldReturn403() throws Exception {
        when(customerService.findCustomerById(1)).thenReturn(customerResponse);
        when(employeeService.getEmployeeById(1)).thenReturn(employeeResponse);
        when(orderService.findOrderById(1)).thenReturn(orderResponse);
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setEmployee_id(1);
        orderRequest.setCustomer_id(1);

        mockMvc.perform(put("/api/v1/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest))
                        .with(user("user").authorities(new SimpleGrantedAuthority("ORDER_VIEW"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateOrder_shouldReturn200() throws Exception {
        when(customerService.findCustomerById(1)).thenReturn(customerResponse);
        when(employeeService.getEmployeeById(1)).thenReturn(employeeResponse);
        when(orderService.findOrderById(1)).thenReturn(orderResponse);
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setEmployee_id(1);
        orderRequest.setCustomer_id(1);

        mockMvc.perform(put("/api/v1/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest))
                        .with(user("user").authorities(new SimpleGrantedAuthority("ORDER_UPDATE"))))
                .andExpect(status().isOk());
    }

    @Test
    void deleteOrder_shouldReturn204() throws Exception {
        when(orderService.findOrderById(1)).thenReturn(orderResponse);

        mockMvc.perform(delete("/api/v1/orders/1")
                        .with(user("user").authorities(new SimpleGrantedAuthority("ORDER_DELETE"))))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteOrder_withoutToken_shouldReturn401() throws Exception {
        when(customerService.findCustomerById(1)).thenReturn(customerResponse);
        mockMvc.perform(delete("/api/v1/orders/1"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void deleteOrder_withoutPermission_shouldReturn403() throws Exception {
        when(customerService.findCustomerById(1)).thenReturn(customerResponse);
        mockMvc.perform(delete("/api/v1/orders/1")
                        .with(user("user").authorities(new SimpleGrantedAuthority("ORDER_VIEW"))))
                .andExpect(status().isForbidden());
    }
}
