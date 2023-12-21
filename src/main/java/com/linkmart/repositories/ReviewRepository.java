package com.linkmart.repositories;

import com.linkmart.models.RequestModel;
import com.linkmart.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
