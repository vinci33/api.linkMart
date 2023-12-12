package com.linkmart.services;

import com.linkmart.models.ImageModel;
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
import java.util.List;

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
    public RequestModel postRequest(String created_by, Integer location_id, Integer category_id,
                                    String item, String url,
                                    Integer quantity, String request_remark, Integer offer_price,
                                    List<MultipartFile> files)
            throws AuthenticationException
    {
        var username = userRepository.findByUserId(created_by);
        var locationName = locationRepository.findByLocationId(location_id);
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
        logger.info(files.toString());

        List<ImageModel> images = new ArrayList<>();
        for (MultipartFile file: files) {
            String imagePath = s3Service.uploadFile(file);
            ImageModel image = new ImageModel();
            image.setImage_path(imagePath);
            image.setRequest_id(newRequest.getId());
            images.add(image);
        }
        newRequest.setImages(images);
        logger.info(newRequest.toString());

        var result = this.requestRepository.saveAndFlush(newRequest);
//        newRequest.setImages(result.getImages());
//        newRequest.setCreated_by(username);
//        newRequest.setCreatedAt(result.getCreatedAt());
//        newRequest.setUpdatedAt(result.getUpdatedAt());

        return newRequest;
    }

    public RequestModel getMyRequest(String userId){
        var result = this.requestRepository.findRequestByUserId(userId);
        return result;
    }
}
