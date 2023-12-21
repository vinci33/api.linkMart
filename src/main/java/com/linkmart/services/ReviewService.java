package com.linkmart.services;

import com.linkmart.models.Review;
import com.linkmart.repositories.RequestRepository;
import com.linkmart.repositories.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ReviewRepository reviewRepository;

    public void saveReview(Review review) {
        try
        {
            reviewRepository.save(review);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
    }
}
