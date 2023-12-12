package com.linkmart.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.linkmart.models.ImageModel;
import com.linkmart.models.ItemDetailModel;
import com.linkmart.models.RequestModel;
import com.linkmart.repositories.LocationRepository;
import com.linkmart.repositories.RequestRepository;
import com.linkmart.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    S3Service s3Service;

    @Transactional
    public RequestModel postRequest(String createdBy, Integer locationId, Integer categoryId,
                                    String itemDetail, String item, String url,
                                    Integer quantity, String requestRemark, Integer offerPrice,
                                    List<MultipartFile> files)
            throws AuthenticationException {
        var newRequest = new RequestModel();
        newRequest.setCreateBy(createdBy);
        newRequest.setLocationId(locationId);
        newRequest.setCategoryId(categoryId);
        newRequest.setItem(item);
        newRequest.setUrl(url);
        newRequest.setQuantity(quantity);
        newRequest.setRequestRemark(requestRemark);
        newRequest.makeRequestCase();
        newRequest.setOfferPrice(offerPrice);

        List<ImageModel> images = new ArrayList<>();
        for (MultipartFile file: files) {
            String imagePath = s3Service.uploadFile(file);
            ImageModel image = new ImageModel();
            image.setImage_path(imagePath);
            image.setRequest_id(newRequest.getId());
            images.add(image);
        }

        newRequest.setImages(images);

        Gson g = new Gson();
        ItemDetailModel itemDetailModel = g.fromJson(itemDetail, ItemDetailModel.class);

        newRequest.setItemDetail(itemDetailModel);

        var result = this.requestRepository.saveAndFlush(newRequest);
        newRequest.setImages(result.getImages());
        newRequest.setCreateBy(result.getCreateBy());
        newRequest.setCreatedAt(result.getCreatedAt());
        newRequest.setUpdatedAt(result.getUpdatedAt());

        return newRequest;
    }


//    @Transactional
//    public RequestModel getRequest(){
//
//    }

    public List<RequestModel> getAllRequest( ){
        var result = this.requestRepository.getAllRequest();
        return result;
    }

    public RequestModel getMyRequest(String userId){
        var result = this.requestRepository.findRequestByUserId(userId);
        return result;
    }


}
