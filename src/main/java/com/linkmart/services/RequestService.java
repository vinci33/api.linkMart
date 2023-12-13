package com.linkmart.services;

import com.google.gson.Gson;
import com.linkmart.dtos.AnotherRequestDto;
import com.linkmart.dtos.RequestDto;
import com.linkmart.models.ImageModel;
import com.linkmart.models.ItemDetailModel;
import com.linkmart.models.RequestModel;
import com.linkmart.repositories.LocationRepository;
import com.linkmart.repositories.RequestRepository;
import com.linkmart.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public RequestModel postRequest(String userId, Integer locationId, Integer categoryId,
                                    String itemDetail, String item, String url,
                                    Integer quantity, String requestRemark, Integer offerPrice,
                                    List<MultipartFile> files)
            throws AuthenticationException {
        var newRequest = new RequestModel();
        newRequest.setCreatedBy(userId);
        newRequest.setLocationId(locationId);
        newRequest.setCategoryId(categoryId);
        newRequest.setItem(item);
        newRequest.setUrl(url);
        newRequest.setQuantity(quantity);

        newRequest.setRequestRemark(requestRemark);
        newRequest.makeRequestCase();
        if (offerPrice != null) {
            newRequest.setOfferPrice(offerPrice);
        }
        MultipartFile firstFile = null;
        List<ImageModel> images = new ArrayList<>();
        for (MultipartFile file: files) {
            String imagePath = s3Service.uploadFile(file);
            ImageModel image = new ImageModel();
            image.setImage_path(imagePath);
            image.setRequest_id(newRequest.getRequestId());
            images.add(image);
            if (firstFile == null) {
                firstFile = file;
                newRequest.setPrimaryImage(imagePath); // Store the first file
            }
        }
        newRequest.setImages(images);


        Gson g = new Gson();
        ItemDetailModel itemDetailModel = g.fromJson(itemDetail, ItemDetailModel.class);
        newRequest.setItemDetail(itemDetailModel);

        var result = this.requestRepository.saveAndFlush(newRequest);
        newRequest.setImages(result.getImages());
        newRequest.setCreatedBy(result.getCreatedBy());
        newRequest.setCreatedAt(result.getCreatedAt());
        newRequest.setUpdatedAt(result.getUpdatedAt());

        return newRequest;
    }

    public List<RequestDto> getAllRequest( ){
        var result = this.requestRepository.getAllRequest();
        return result;
    }

    public List<RequestDto> getAllMyRequest(String userId) {
        var result = this.requestRepository.getAllRequestByUserId(userId);
        return result;
    }


//    "/api/request?p={page}&category={category}&location={location}"
    public Page<AnotherRequestDto> getRequestsByCategoryAndLocation(String category, String location, int page) {
        if (page < 0) {
            throw new IllegalArgumentException("Page number cannot be less than zero.");
        }
        Pageable pageable15 = PageRequest.of(page, 15, Sort.by("createdAt").descending());
        var requestPage =  requestRepository.findRequestByCategoryAndLocation(category, location,pageable15);
        if (page > 0 && !requestPage.hasContent()) {
            throw new IllegalArgumentException("Page number is greater than the total number of pages.");
        }
        return requestPage;
    }
}
