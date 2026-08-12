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
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Integer customer_id = orderRequest.getCustomer_id();
        Integer employee_id = orderRequest.getEmployee_id();

        validateCustomerAndEmployee(customer_id, employee_id);

        Customer customer = customerRepository.findById(customer_id).get();
        Employee employee = employeeRepository.findById(employee_id).get();

        Order order = new Order();
        order.setOrdered_customer(customer);
        order.setHandled_employee(employee);
        orderRepository.save(order);
        return getOrderResponse(order);
    }


    @Override
    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll().stream().map(OrderServiceImpl::getOrderResponse).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> findAllOrdersByEmployeeId(Integer employee_id) {
        if (!employeeRepository.existsById(employee_id)) {
            throw new BusinessValidationException("Employee with id " + employee_id + " not found", Map.of("employee_id", employee_id.toString()));
        }
        Employee employee = employeeRepository.findById(employee_id).get();
        return employee.getOrders().stream().map(OrderServiceImpl::getOrderResponse).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> findByOrderDateBetween(Date startDate, Date endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate).stream().map(OrderServiceImpl::getOrderResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse findOrderById(Integer order_id) {
        Order foundOrder = orderRepository.findById(order_id).orElseThrow(() -> new BusinessValidationException("Order with id " + order_id + " not found", Map.of("order_id", order_id.toString())));
        return getOrderResponse(foundOrder);
    }

    @Override
    public OrderResponse updateOrderById(Integer order_id, OrderRequest orderRequest) {
        Order orderToUpdate = orderRepository.findById(order_id).orElseThrow(() -> new BusinessValidationException("Order with id " + order_id + " not found", Map.of("order_id", order_id.toString())));
        Integer customer_id = orderRequest.getCustomer_id();
        Integer employee_id = orderRequest.getEmployee_id();
        Date order_date = orderRequest.getOrder_date();
        validateCustomerAndEmployee(customer_id, employee_id);
        orderToUpdate.setOrder_date(order_date);
        orderToUpdate.setOrdered_customer(customerRepository.findById(customer_id).get());
        orderToUpdate.setHandled_employee(employeeRepository.findById(employee_id).get());
        orderRepository.save(orderToUpdate);
        return getOrderResponse(orderToUpdate);
    }

    @Override
    public void deleteOrderById(Integer order_id) {
        Order orderToDelete = orderRepository.findById(order_id).orElseThrow(() -> new BusinessValidationException("Order with id " + order_id + " not found", Map.of("order_id", order_id.toString())));
        orderRepository.delete(orderToDelete);
    }

    private void validateCustomerAndEmployee(Integer customer_id, Integer employee_id) {
        if (!customerRepository.existsById(customer_id)) {
            throw new BusinessValidationException("Customer with id " + customer_id + " not found", Map.of("customer_id", customer_id.toString()));
        } else if (!employeeRepository.existsById(employee_id)) {
            throw new BusinessValidationException("Employee with id " + employee_id + " not found", Map.of("employee_id", employee_id.toString()));
        }
    }

    private static OrderResponse getOrderResponse(Order order) {
        Integer customerId = (order.getOrdered_customer() != null)
                ? order.getOrdered_customer().getCustomerId()
                : null;

        Integer employeeId = (order.getHandled_employee() != null)
                ? order.getHandled_employee().getEmployee_id()
                : null;

        return OrderResponse.builder()
                .orderId(order.getOrder_id())
                .order_date(order.getOrder_date())
                .customer_id(customerId)
                .employee_id(employeeId)
                .build();
    }
}
