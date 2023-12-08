package com.linkmart.service;

import com.linkmart.models.ImageModel;
import com.linkmart.models.RequestModel;
import com.linkmart.repositories.ImageRepository;
import com.linkmart.repositories.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;

@Service
public class RequestService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    S3Service s3Service;

    @Transactional
    public RequestModel postRequest(String created_by, Integer location_id, Integer category_id,
                                    String item, String url,
                                    Integer quantity, String request_remark, Integer offer_price,
                                    MultipartFile file)
            throws AuthenticationException
    {
        var newRequest = new RequestModel();
        newRequest.setCreated_by(created_by);
        newRequest.setLocation_id(location_id);
        newRequest.setCategory_id(category_id);
        newRequest.setItem(item);
        newRequest.setUrl(url);
        newRequest.setQuantity(quantity);
        newRequest.setRequest_remark(request_remark);
        newRequest.makeRequestCase();
        newRequest.setOffer_price(offer_price);

        ImageModel image = new ImageModel();
        String imagePath = s3Service.uploadFile(file);
        image.setImage_path(imagePath);
        newRequest = this.requestRepository.saveAndFlush(newRequest);
        this.imageRepository.save(image);
        return newRequest;
    }

    public ImageModel uploadImage(MultipartFile image, String request_id){
        var imagePath = s3Service.uploadFile(image);
        var newImage = new ImageModel();
        newImage.setImage_path(imagePath);
        newImage.setRequest_id(request_id);
        return newImage;
    }
}
