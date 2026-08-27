package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("SELECT o FROM Order o WHERE o.order_date BETWEEN :startDate AND :endDate")
    List<Order> findByOrderDateBetween(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );
}
