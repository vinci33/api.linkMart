package com.linkmart.repositories;

import com.linkmart.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus,String> {
}
