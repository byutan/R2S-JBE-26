package com.example.demo.service.impl;

import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Order;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OrderRepository;
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
public class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    private Order order;
    private Customer customer;
    private Employee employee;
    private OrderRequest request;
    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmailAddress("jdoe@email.com");
        customer.setPhoneNumber("0931311482");

        employee = new Employee();
        employee.setEmployee_id(1);
        employee.setFirst_name("John");
        employee.setLast_name("Doe");
        employee.setBirth_date(Date.valueOf("2005-12-28"));
        employee.setSupervisor(null);

        order = new Order();
        order.setOrdered_customer(customer);
        order.setHandled_employee(employee);

        request = new OrderRequest();
        request.setCustomer_id(1);
        request.setEmployee_id(1);
    }

    @Test
    void getAllOrders_shouldReturnList() {
        when(orderRepository.findAll()).thenReturn(List.of(order, order));
        List<OrderResponse> result = orderServiceImpl.findAllOrders();
        assertEquals(2, result.size());
    }

    @Test
    void getAllOrders_shouldReturnEmptyList() {
        when(orderRepository.findAll()).thenReturn(List.of());
        List<OrderResponse> result = orderServiceImpl.findAllOrders();
        assertEquals(0, result.size());
    }

    @Test
    void getOrderById_shouldReturnOrder() {
        when(orderRepository.findById(1)).thenReturn(Optional.ofNullable(order));
        OrderResponse result =  orderServiceImpl.findOrderById(1);
        assertEquals(order.getOrdered_customer().getCustomerId(), result.getCustomer_id());
    }

    @Test
    void getOrderById_withWrongId_shouldThrowException() {
        when(orderRepository.findById(1)).thenReturn(Optional.empty());
        Exception exception = assertThrows(BusinessValidationException.class, () -> orderServiceImpl.findOrderById(1));
        assertEquals("Order with id 1 not found", exception.getMessage());
    }

    @Test
    void getOrderByDate_shouldReturnList() {
        when(orderRepository.findByOrderDateBetween(any(), any())).thenReturn(List.of(order, order));
        Date startDate = Date.valueOf("2005-12-28");
        Date endDate = Date.valueOf("2005-12-29");
        List<OrderResponse> result = orderServiceImpl.findByOrderDateBetween(startDate, endDate);
        assertEquals(2, result.size());
        verify(orderRepository).findByOrderDateBetween(startDate, endDate);
    }
    @Test
    void createOrder_shouldCreateOrder() {
        when(customerRepository.existsById(any())).thenReturn(true);
        when(employeeRepository.existsById(any())).thenReturn(true);
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        OrderResponse result = orderServiceImpl.createOrder(request);
        assertEquals(order.getOrdered_customer().getCustomerId(), result.getCustomer_id());
    }

    @Test
    void createOrder_withoutCustomer_shouldThrowException() {
        when(customerRepository.existsById(any())).thenReturn(false);
        Exception exception = assertThrows(BusinessValidationException.class, () -> orderServiceImpl.createOrder(request));
        assertEquals("Customer with id 1 not found", exception.getMessage());
    }

    @Test
    void createOrder_withoutEmployee_shouldThrowException() {
        when(customerRepository.existsById(any())).thenReturn(true);
        when(employeeRepository.existsById(any())).thenReturn(false);
        Exception exception = assertThrows(BusinessValidationException.class, () -> orderServiceImpl.createOrder(request));
        assertEquals("Employee with id 1 not found", exception.getMessage());
    }

    @Test
    void updateOrder_shouldUpdateOrder() {
        when(customerRepository.existsById(any())).thenReturn(true);
        when(employeeRepository.existsById(any())).thenReturn(true);
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(orderRepository.findById(1)).thenReturn(Optional.ofNullable(order));
        OrderResponse result = orderServiceImpl.updateOrderById(1, request);
        assertEquals(order.getOrdered_customer().getCustomerId(), result.getCustomer_id());
    }

    @Test
     void updateOrder_withoutCustomer_shouldThrowException() {
        when(orderRepository.findById(1)).thenReturn(Optional.ofNullable(order));
        when(customerRepository.existsById(any())).thenReturn(false);
        Exception exception = assertThrows(BusinessValidationException.class, () -> orderServiceImpl.updateOrderById(1, request));
        assertEquals("Customer with id 1 not found", exception.getMessage());
    }

    @Test
    void updateOrder_withoutEmployee_shouldThrowException() {
        when(orderRepository.findById(1)).thenReturn(Optional.ofNullable(order));
        when(customerRepository.existsById(any())).thenReturn(true);
        when(employeeRepository.existsById(any())).thenReturn(false);
        Exception exception = assertThrows(BusinessValidationException.class, () -> orderServiceImpl.updateOrderById(1, request));
        assertEquals("Employee with id 1 not found", exception.getMessage());
    }

    @Test
    void updateOrder_withWrongId_shouldThrowException() {
        when(orderRepository.findById(1)).thenReturn(Optional.empty());
        Exception exception = assertThrows(BusinessValidationException.class, () -> orderServiceImpl.updateOrderById(1, request));
        assertEquals("Order with id 1 not found", exception.getMessage());
    }

    @Test
    void deleteOrder_shouldDeleteOrder() {
        when(orderRepository.findById(1)).thenReturn(Optional.ofNullable(order));
        orderServiceImpl.deleteOrderById(1);
        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    void deleteOrder_withWrongId_shouldThrowException() {
        when(orderRepository.findById(1)).thenReturn(Optional.empty());
        Exception exception = assertThrows(BusinessValidationException.class, () ->orderServiceImpl.deleteOrderById(1));
        assertEquals("Order with id 1 not found", exception.getMessage());
    }
}
