package com.linkmart.services;

import com.linkmart.models.Review;
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

    public Float getAverageEfficiency(String providerId) {
        try
        {
            return reviewRepository.getAverageEfficiency(providerId);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            return null;
        }
    }

    public Float getAverageAttitude(String providerId) {
        try
        {
            return reviewRepository.getAverageAttitude(providerId);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            return null;
        }
    }

    public Integer getReviewCount(String providerId) {
        try
        {
            return reviewRepository.getReviewCount(providerId);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            return null;
        }
    }
}
