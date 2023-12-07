package com.linkmart.service;

import com.linkmart.models.Request;
import com.linkmart.repositories.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import java.security.Timestamp;

@Service
public class RequestService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    S3Service s3Service;

    @Transactional
    public Request postRequest(String created_by, Integer location_id, Integer category_id,
                               String item, MultipartFile image, String url, Integer quantity, String request_remark, Integer offer_price)
            throws AuthenticationException
    {
        var newRequest = new Request();
        newRequest.setCreated_by(created_by);
        newRequest.setLocation_id(location_id);
        newRequest.setCategory_id(category_id);
        newRequest.setItem(item);
        newRequest.setUrl(url);
        newRequest.setQuantity(quantity);
        newRequest.setRequest_remark(request_remark);
        newRequest.makeRequestCase();
        newRequest.setOffer_price(offer_price);
        var imagePath = s3Service.uploadFile(image);
        newRequest.setImage(imagePath);
        newRequest = this.requestRepository.saveAndFlush(newRequest);
        return newRequest;
    }

    public String upload(MultipartFile image){
        var imagePath = s3Service.uploadFile(image);
        logger.info(imagePath);
        return imagePath;
    }
}
