package com.linkmart.repositories;
import com.linkmart.dtos.AnotherRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.linkmart.dtos.RequestDto;
import com.linkmart.models.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface RequestRepository extends JpaRepository<RequestModel, Integer> {
    @Query(value = """
            Select * from request where created_by = :userId
             """, nativeQuery = true)
    List<RequestModel> findRequestByUserId(@Param("userId") String userId);

    @Query(value = """
                    SELECT r.id as requestId,  l.location_name as locationName,\s
                               r.item, r.primary_image as primaryImage, r.offer_price as offerPrice,\s
                               r.created_at as createdAt, r.updated_at as updatedAt,
                               u.username as createdBy
                        FROM request r
                        JOIN location l ON r.location_id = l.id
                        JOIN users u ON r.created_by = u.id
            LIMIT 30
            """, nativeQuery = true)
    List<RequestDto> getAllRequest();

    @Query(value = """
                    SELECT r.id as requestId,  l.location_name as locationName,\s
                               r.item, r.primary_image as primaryImage, r.offer_price as offerPrice,\s
                               r.created_at as createdAt, r.updated_at as updatedAt,
                               u.username as createdBy
                        FROM request r
                        JOIN location l ON r.location_id = l.id
                        JOIN users u ON r.created_by = :userId
            LIMIT 30
            """, nativeQuery = true)
    List<RequestDto> getAllRequestByUserId(@Param("userId") String userId);
    //01HHHG4RGFN0JHKWSJMY3X0E9Q


    @Query(value ="""
    SELECT r.id as requestId, c.category_name as categoryName, l.location_name as locationName,
           u.username as createdBy, r.item, r.primary_image as primaryImage, r.offer_price as offerPrice,
           r.created_at as createdAt, r.updated_at as updatedAt
    FROM request r
    JOIN category c ON r.category_id = c.id
    JOIN location l ON r.location_id = l.id
    JOIN users u ON r.created_by = u.id
    WHERE (:categoryName IS NULL OR c.category_name = :categoryName)
    AND (:locationName IS NULL OR l.location_name = :locationName)
    """, nativeQuery =true)
    Page<AnotherRequestDto> findRequestByCategoryAndLocation(@Param("category") String category, @Param("location") String location, Pageable pageable);
}