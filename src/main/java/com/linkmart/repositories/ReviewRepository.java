package com.linkmart.repositories;

import com.linkmart.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query(value = """
            SELECT * FROM review WHERE review.orders_id = :orderId
            """
            , nativeQuery = true)
    Review findReviewByOrderId(String orderId);

    @Query(value = """
            SELECT AVG(review_efficiency)
            FROM review
            WHERE provider_id = :providerId
            """
            , nativeQuery = true)
    Float getAverageEfficiency(String providerId);

    @Query(value = """
            SELECT AVG(review_attitude)
            FROM review
            WHERE provider_id = :providerId
            """
            , nativeQuery = true)
    Float getAverageAttitude(String providerId);

    @Query(value = """
            SELECT COUNT(id)
            FROM review
            WHERE provider_id = :providerId
            """
            , nativeQuery = true)
    Integer getReviewCount(String providerId);
}
