package com.linkmart.repositories;

import com.linkmart.dtos.OfferDto;
import com.linkmart.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, String> {
    @Query(value =
                """
                SELECT *
                FROM offer
                WHERE offer.request_id = :requestId
                AND offer.offer_status_id = 1
                ORDER BY created_at DESC
                """, nativeQuery = true)
    List<Offer> findByRequestId(@Param("requestId") String requestId);

    @Query(value =
            """
            SELECT *
            FROM offer
            WHERE offer.provider_id = :providerId
            AND offer.request_id = :requestId
            AND offer.offer_status_id = 1
            ORDER BY created_at DESC
            """, nativeQuery = true)
    Offer findByProviderIdAndRequestId(@Param("providerId") String providerId, @Param("requestId") String requestId);

    @Query(value =
            """
            SELECT
               offer.id as offerId,
               request.id as requestId,
               users.username as createdBy,
               request.item,
               request.primary_image as primaryImage,
               offer.price,
               offer.estimated_process_time as estimatedProcessTime,
               status.status_name as offerStatus
               FROM offer
               JOIN request ON offer.request_id = request.id
               JOIN users ON request.created_by = users.id
               JOIN status ON offer.offer_status_id = status.id
               WHERE offer.provider_id = :providerId
               AND offer.offer_status_id = 1 OR offer.offer_status_id = 6
            ORDER BY offer.updated_at DESC
            """, nativeQuery = true)
    List<OfferDto> findActiveByProviderId(@Param("providerId") String providerId);

    @Query(value =
            """
            SELECT
            *
            From offer
            WHERE offer.id = :offerId
            """, nativeQuery = true)
    Offer findOfferById(@Param("offerId") String offerId);

    @Modifying
    @Query(value =
            """
            UPDATE
            offer
            SET offer_status_id = :offerStatusId
            WHERE id = :offerId
            """, nativeQuery = true)
    void updateOfferStatus(@Param("offerId") String offerId, @Param("offerStatusId") Integer offerStatusId);

    Offer findOfferByOfferId(String offerId);

    List<Offer> findOfferByRequestIdAndOfferStatusId(String requestId, Integer offerStatusId);

    @Query(value =
            """
            SELECT
               offer.id as offerId,
               request.id as requestId,
               users.username as createdBy,
               request.item,
               request.primary_image as primaryImage,
               offer.price,
               offer.estimated_process_time as estimatedProcessTime,
               status.status_name as offerStatus
               FROM offer
               JOIN request ON offer.request_id = request.id
               JOIN users ON request.created_by = users.id
               JOIN status ON offer.offer_status_id = status.id
               WHERE offer.provider_id = :providerId
               AND offer.offer_status_id = :offerStatusId
            ORDER BY offer.updated_at DESC
            """, nativeQuery = true)
    List<OfferDto> findOfferAndRequestByProviderIdAndStatus(@Param("providerId") String providerId, @Param("offerStatusId") Integer offerStatusId);

    @Modifying
    @Query(value =
            """
            Update offer
            SET offer_status_id = 4
            WHERE offer.request_id = :requestId
            """, nativeQuery = true)
    void updateOfferStatusAfterRequestDeleted(@Param("requestId") String requestId);

    @Query(value =
            """
            SELECT COUNT(offer.id) as offerCount
            FROM offer
            WHERE offer.provider_id = :providerId
            AND offer.offer_status_id = 1
            """, nativeQuery = true)
    Integer getPendingOfferByProviderId(@Param("providerId") String providerId);
}
