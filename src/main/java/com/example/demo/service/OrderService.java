package com.example.demo.service;

import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    List<OrderResponse> findAllOrders();
    List<OrderResponse> findAllOrdersByEmployeeId(Integer employee_id);
    List<OrderResponse> findByOrderDateBetween(Date startDate, Date endDate);
    OrderResponse findOrderById(Integer order_id);
    OrderResponse updateOrderById(Integer order_id, OrderRequest orderRequest);
    void deleteOrderById(Integer order_id);
}
