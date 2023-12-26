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
}
