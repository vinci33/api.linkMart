package com.linkmart.repositories;

import com.linkmart.dtos.OrdersDto;
import com.linkmart.models.Orders;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, String> {
    Orders findOrdersById(String orderId);


    @Query(value =
            """
                SELECT
                    o.id AS orderId,
                    os.order_status AS orderStatus,
                    o.created_at AS createdAt,
                    p.id AS providerId,
                    u.username AS providerName,
                    r.item AS item,
                    r.primary_image AS primaryImage,
                    r.quantity AS quantity,
                    of.price AS price,
                    of.estimated_process_time AS estimatedProcessTime
                FROM
                    orders o
                JOIN
                    offer of ON o.offer_id = of.id
                JOIN
                    provider p ON of.provider_id = p.id
                JOIN
                    users u ON p.user_id = u.id
                JOIN
                    request r ON of.request_id = r.id
                JOIN
                    order_status os ON o.order_status_id = os.id
                WHERE
                     u.id = :userId
                    ORDER BY o.created_at DESC
                    """, nativeQuery = true)
    List<OrdersDto> findOrdersByUserId(String userId);

    @Query(value =
            """
                SELECT
                    o.id AS orderId,
                    os.order_status AS orderStatus,
                    o.created_at AS createdAt,
                    p.id AS providerId,
                    u.username AS providerName,
                    r.item AS item,
                    r.primary_image AS primaryImage,
                    r.quantity AS quantity,
                    of.price AS price,
                    of.estimated_process_time AS estimatedProcessTime
                FROM
                    orders o
                JOIN
                    offer of ON o.offer_id = of.id
                JOIN
                    provider p ON of.provider_id = p.id
                JOIN
                    users u ON p.user_id = u.id
                JOIN
                    request r ON of.request_id = r.id
                JOIN
                    order_status os ON o.order_status_id = os.id
                WHERE
                     u.id = :userId AND
                    os.order_status IN :orderStatuses
                    ORDER BY o.created_at DESC
                    """, nativeQuery = true)
    List<OrdersDto> findOrdersByUserIdAndStatus(String userId, List<String> orderStatuses);
}

