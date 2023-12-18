package com.linkmart.repositories;

import com.linkmart.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, String> {
    Orders findOrdersById(String orderId);

}
