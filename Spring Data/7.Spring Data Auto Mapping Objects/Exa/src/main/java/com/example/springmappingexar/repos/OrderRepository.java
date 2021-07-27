package com.example.springmappingexar.repos;

import com.example.springmappingexar.models.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
