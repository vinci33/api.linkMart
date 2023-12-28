package com.linkmart.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class S3Service {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AmazonS3 s3Client;

    public String uploadFile(MultipartFile file) {
        try {
            var path = "profiles/profile-" + System.currentTimeMillis() + "." +
                    StringUtils.getFilenameExtension(file.getOriginalFilename());
            this.s3Client.putObject("cdn.linkmart.com", path,
                    file.getInputStream(),
                    new ObjectMetadata());
            return "http://cdn.linkmart.com.s3-website-ap-southeast-1.amazonaws.com/"+ path;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Failed to upload file"
            );
        }
    }
    public String uploadShipmentFile(MultipartFile file) {
        try {
            var path = "profiles/shipment" + System.currentTimeMillis() + "." +
                    StringUtils.getFilenameExtension(file.getOriginalFilename());
            this.s3Client.putObject("cdn.linkmart.com", path,
                    file.getInputStream(),
                    new ObjectMetadata());
            return "http://cdn.linkmart.com.s3-website-ap-southeast-1.amazonaws.com/"+ path;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Failed to upload file"
            );
        }
    }
}
