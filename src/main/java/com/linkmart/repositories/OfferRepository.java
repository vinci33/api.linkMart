package com.linkmart.repositories;

import com.linkmart.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, String> {
    @Query(value =
                """
                SELECT *
                FROM offers
                WHERE request_id = :requestId
                ORDER BY created_at DESC
                """, nativeQuery = true)
    Offer findByRequestId(@Param("requestId") String requestId);
}
