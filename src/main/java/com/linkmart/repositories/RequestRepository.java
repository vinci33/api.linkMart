package com.linkmart.repositories;
import com.linkmart.dtos.AnotherRequestDto;
import com.linkmart.dtos.RequestDto;
import com.linkmart.models.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public interface RequestRepository extends JpaRepository<RequestModel, Integer> {

    @Query(value = """
                    SELECT r.id as requestId,
                     u.username as createdBy,
                     l.location_name as locationName,
                     r.item,
                     r.primary_image as primaryImage,
                     r.offer_price as offerPrice,
                     r.created_at as createdAt,
                     r.updated_at as updatedAt
                     FROM request r
                     JOIN location l ON r.location_id = l.id
                     JOIN users u ON r.created_by = u.id
                     WHERE r.is_active = true
                     ORDER BY r.created_at DESC
            """, nativeQuery = true)
    List<RequestDto> getAllRequest();

    @Query(value = """
                    SELECT
                        request.id as requestId,
                        users.username as createdBy,
                        request.item,
                        request.primary_image as primaryImage,
                        request.offer_price as offerPrice,
                        request.created_at as createdAt,
                        request.updated_at as updatedAt,
                        location.location_name as locationName
                    FROM request
                    JOIN users ON request.created_by = users.id
                    JOIN location ON request.location_id = location.id
                    WHERE request.created_by = :userId
                    AND request.is_active = :isActive
                    ORDER BY request.updated_at DESC
            LIMIT 30
            """, nativeQuery = true)
    List<RequestDto> getAllRequestByUserId(@Param("userId") String userId, @Param("isActive") Boolean isActive);

    @Query(value = """
                    SELECT
                        *
                        FROM request
                        WHERE request.id = :requestId
                        ORDER BY updated_at DESC
            """, nativeQuery = true)
    RequestModel getRequestByRequestId(@Param("requestId") String requestId);
    @Query(value = """
                    SELECT
                        created_by
                        FROM request
                        WHERE request.id = :requestId
                        ORDER BY updated_at DESC
            """, nativeQuery = true)
    String findCreatedByByRequestId(@Param("requestId") String requestId);

    @Modifying
    @Query(value = """
                    UPDATE request
                    SET is_active = false,
                    updated_at = NOW()
                    WHERE id = :requestId
            """, nativeQuery = true)
    void updateRequestIsActiveByRequestId(@Param("requestId") String requestId);

    @Modifying
    @Query(value = """
                    UPDATE request
                    SET has_offer = true,
                    updated_at = NOW()
                    WHERE id = :requestId
            """, nativeQuery = true)
    void updateRequestStatusIdByRequestId(@Param("requestId") String requestId);

    RequestModel findRequestModelByRequestId(String requestId);

    @Modifying
    @Query(value = """
            SELECT
                request.id as requestId,
                users.username as createdBy,
                request.item,
                request.primary_image as primaryImage,
                request.offer_price as offerPrice,
                request.created_at as createdAt,
                request.updated_at as updatedAt,
                request.location_id as locationName
            FROM request
            JOIN users ON request.created_by = users.id
            JOIN location ON request.location_id = location.id
            WHERE request.created_by = :userId
                AND request.is_active = false
            ORDER BY request.updated_at DESC
            """, nativeQuery = true)
    List<RequestDto> getAllRequestHistoryByUserId(@Param("userId") String userId);

    @Query(value ="""
    SELECT r.id as requestId, c.category_name as categoryName, l.location_name as locationName,
           u.username as createdBy, r.item, r.primary_image as primaryImage, r.offer_price as offerPrice,
           r.created_at as createdAt, r.updated_at as updatedAt
    FROM request r
    JOIN category c ON r.category_id = c.id
    JOIN location l ON r.location_id = l.id
    JOIN users u ON r.created_by = u.id
    WHERE category_name = IF(:categoryName IS NULL, '', :categoryName)
    WHERE (COALESCE(:categoryName, '') = '' OR c.category_name IN :categoryName)
    """, nativeQuery =true)
    List<AnotherRequestDto> findRequestByCategoryAndLocation(@Param("categoryName") List<String> category);
}
