package com.linkmart.repositories;

import com.fasterxml.jackson.databind.JsonNode;
import com.linkmart.dtos.OrdersByOrderIdImageDto;
import com.linkmart.dtos.OrdersByOrderIdWithoutImageDto;
import com.linkmart.dtos.OrdersDto;
import com.linkmart.models.ItemDetailModel;
import com.linkmart.models.Orders;
import io.swagger.v3.core.util.Json;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

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

    @Query(value =
            """
                SELECT
                     o.id AS orderId,
                     o.created_at AS createdAt,
                     o.updated_at AS updatedAt,
                     os.order_status AS orderStatus,
                     of.provider_id AS providerId,
                     of.request_id AS requestId,
                     of.price AS price,
                     of.estimated_process_time AS estimatedProcessTime,
                     u.username AS providerName,
                     r.created_by AS createdBy,
                     r.item AS item,
                     r.primary_image AS primaryImage,
                     r.item_detail AS itemDetail,
                     r.quantity AS quantity,
                     r.url AS url,
                     r.request_remark AS requestRemark,
                     l.location_name AS locationName
                 FROM
                     orders o
                 JOIN
                     offer of ON o.offer_id = of.id
                 JOIN
                    order_status os ON o.order_status_id = os.id
                 JOIN
                     provider p ON of.provider_id = p.id
                 JOIN
                     users u ON p.user_id = u.id
                 JOIN
                     request r ON of.request_id = r.id
                 
                 JOIN
                     location l ON r.location_id = l.id
                 WHERE
                     o.id = :orderId
                    """, nativeQuery = true)
    OrdersByOrderIdWithoutImageDto findOrdersDetailByOrderId(String orderId);

    @Query(value =
            """ 
                SELECT
                    i.image_path AS image
                FROM
                    image i
                JOIN
                    request r ON i.request_id = r.id
                JOIN
                    offer of ON r.id = of.request_id
                JOIN
                    orders o ON of.id = o.offer_id
                WHERE
                    o.id = :orderId
            """, nativeQuery = true)
    List<String> findImagesByOrderId(String orderId);

    @Query(value =
            """
                SELECT
                    r.item_detail AS itemDetail
                FROM
                    request r
                JOIN
                    offer of ON r.id = of.request_id
                JOIN
                    orders o ON of.id = o.offer_id
                WHERE
                    o.id = :orderId
            """, nativeQuery = true)
    String findItemDetailByOrderId(String orderId);


}

