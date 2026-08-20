package com.example.demo.controller;

import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@Validated
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PreAuthorize("hasAuthority('ORDER_CREATE')")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequest));
    }
    @GetMapping
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PreAuthorize("hasAuthority('ORDER_VIEW')")
    public ResponseEntity<List<OrderResponse>> getAllOrder() {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.findAllOrders());
    }
    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PreAuthorize("hasAuthority('ORDER_VIEW')")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrderById(id));
    }
    @GetMapping("/by-employee/{employeeId}")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('ORDER_ADMIN_VIEW')")
    public ResponseEntity<List<OrderResponse>> getOrdersByEmployee(@PathVariable Integer employeeId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAllOrdersByEmployeeId(employeeId));
    }

    @GetMapping("/between")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('ORDER_ADMIN_VIEW')")
    public ResponseEntity<List<OrderResponse>> getBetween(
            @RequestParam @NotNull(message = "From date is required")
            @PastOrPresent(message = "Start date must be in the past or present") Date startDate,
            @RequestParam @NotNull(message = "From date is required")
            @PastOrPresent(message = "End date must be in the past or present") Date endDate) {
        return ResponseEntity.ok(orderService.findByOrderDateBetween(startDate, endDate));
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('ORDER_UPDATE')")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Integer id, @RequestBody @Valid OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderById(id, orderRequest));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('ORDER_DELETE')")
    public ResponseEntity<OrderResponse> deleteOrderById(@PathVariable Integer id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
