package com.linkmart.services;

import com.linkmart.models.RequestModel;
import com.linkmart.repositories.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ImageRepository imageRepository;

    @Transactional
    public void deleteRequestImage(Integer imageId) throws Exception {
        try {
            imageRepository.deleteById(imageId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
