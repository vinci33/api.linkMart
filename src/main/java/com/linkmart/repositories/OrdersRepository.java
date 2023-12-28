package com.linkmart.repositories;

import com.fasterxml.jackson.databind.JsonNode;
import com.linkmart.dtos.OrdersByOrderIdImageDto;
import com.linkmart.dtos.OrdersByOrderIdWithoutImageDto;
import com.linkmart.dtos.OrdersDto;
import com.linkmart.models.ItemDetailModel;
import com.linkmart.models.Orders;
import com.linkmart.models.User;
import io.swagger.v3.core.util.Json;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrdersRepository extends JpaRepository<Orders, String> {
    Orders findOrdersById(String orderId);

    @Query(value = """
            SELECT * FROM orders
            WHERE orders.id = :orderId
            """, nativeQuery = true)
    Orders getOneByOfferId(@Param("orderId") String orderId);

    @Query(value = """
            SELECT id FROM orders
            WHERE orders.offer_id = :offerId
            """, nativeQuery = true)
    String findOrderIdByOfferId(@Param("offerId") String offerId);

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
                     o.user_address_id AS userAddressId,
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
                     l.location_name AS locationName,
                     o.shipping_order_no AS shippingOrderNo,
                     lc.company_name AS logisticCompanyName,
                     lc.company_url AS logisticCompanyUrl
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
                 LEFT JOIN
                     logistic_company lc ON o.logistic_company_id = lc.id
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
                     r.created_by = :userId AND
                    os.order_status IN :orderStatuses
                    ORDER BY o.created_at DESC
                    """, nativeQuery = true)
    List<OrdersDto> findOrdersByUserIdAndStatusFromUser(String userId, List<String> orderStatuses);

    List<Orders> findOrdersByOrderStatusId(Integer orderStatusId);

    @Query(value =
            """
                SELECT
                    r.created_by AS userId
                FROM
                    orders o
                JOIN
                    offer of ON o.offer_id = of.id
                JOIN
                    request r ON of.request_id = r.id
                WHERE
                     o.id = :orderId
                    """, nativeQuery = true)
    String findUserIdByOrderId(String orderId);
                     

    @Query(value = """
            SELECT Count(Offer.id)
            FROM Offer
            JOIN Orders ON Offer.id = Orders.offer_id
            WHERE Offer.provider_id = :providerId
            AND Orders.order_status_id = 4 OR Orders.order_status_id = 6
            """, nativeQuery = true)
    Integer getCompletedOrderByProviderId(@Param("providerId") String providerId);

    @Query(value = """
            SELECT SUM(Offer.price)
            FROM Offer
            JOIN Orders ON Offer.id = Orders.offer_id
            WHERE Offer.provider_id = :providerId
            AND Orders.order_status_id = 4 OR Orders.order_status_id = 6
            """, nativeQuery = true)
    Integer calculateBalanceByProviderId(@Param("providerId") String providerId);

    @Query(value = """
            SELECT
            Count(Offer.id)
            FROM Offer
            JOIN Orders ON Offer.id = Orders.offer_id
            WHERE Offer.provider_id = :providerId
            AND Orders.order_status_id = 2 OR Orders.order_status_id = 3
                """, nativeQuery = true)
    Integer getActiveOrderByProviderId(@Param("providerId") String providerId);
}

