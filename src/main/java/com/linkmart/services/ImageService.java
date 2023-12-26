package com.linkmart.services;

import com.linkmart.models.ImageModel;
import com.linkmart.models.RequestModel;
import com.linkmart.repositories.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class ImageService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    RequestService requestService;

    @Transactional
    public void updateRequestImage(Integer imageId) throws Exception {
        try {
            imageRepository.updateImageIsActiveByImageId(imageId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void setPrimaryImage(Integer imageId) throws Exception {
        try {
            logger.info("imageId: " + imageId);
            String requestId = imageRepository.findRequestIdByImageId(imageId);
            logger.info("requestId: " + requestId);
            List<Integer> Images = imageRepository.findImageByRequestId(requestId);
            logger.info("Images: " + Images);
            for (Integer image : Images) {
                logger.info("image: " + image);
                imageRepository.updateImageIsNotPrimaryByImageId(image);
                }
            imageRepository.updateImageIsPrimaryByImageId(imageId);
            var primaryImagePath = imageRepository.findImagePathByImageId(imageId);
            requestService.updateRequestPrimaryImage(requestId, primaryImagePath);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
