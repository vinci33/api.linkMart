package com.linkmart.services;

import com.google.gson.Gson;
import com.linkmart.dtos.AnotherRequestDto;
import com.linkmart.dtos.OneRequestDto;
import com.linkmart.dtos.RequestDto;
import com.linkmart.models.ImageModel;
import com.linkmart.models.ItemDetailModel;
import com.linkmart.models.RequestModel;
import com.linkmart.repositories.CategoryRepository;
import com.linkmart.repositories.LocationRepository;
import com.linkmart.repositories.RequestRepository;
import com.linkmart.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    @PersistenceContext
    EntityManager em;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    S3Service s3Service;

    @Transactional
    public RequestModel postRequest(String userId, Integer locationId, Integer categoryId,
                                    String itemDetail, String item, String url,
                                    Integer quantity, String requestRemark, Integer offerPrice,
                                    List<MultipartFile> files)
            throws Exception {
        try {
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
        } catch (Exception e) {
            throw new Exception("Cannot create request");
        }
    }


    public List<RequestDto> getAllRequest( ) throws Exception {
        try {
            var result = this.requestRepository.getAllRequest();
            return result;
        } catch (Exception e) {
            throw new Exception("Cannot get all request");
        }
    }

    public List<RequestDto> getAllMyRequest(String userId) {
        try {
            var result = this.requestRepository.getAllRequestByUserId(userId);
            return result;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot get all my request");
        }
    }

    public OneRequestDto getOneRequest(String requestId) throws Exception {
        try {
            var result = this.requestRepository.getRequestByRequestId(requestId);
            var oneRequest = new OneRequestDto();
            var location = this.locationRepository.findByLocationId(result.getLocationId());
            oneRequest.setRequestId(result.getRequestId());
            oneRequest.setCreatedBy(userRepository.findByUserId(result.getCreatedBy()));
            oneRequest.setLocationId(result.getLocationId());
            oneRequest.setLocationName(location);
            oneRequest.setCategoryId(result.getCategoryId());
            oneRequest.setCategoryName(categoryRepository.findCategoryNameByCategoryId(result.getCategoryId()));
            oneRequest.setPrimaryImage(result.getPrimaryImage());
            oneRequest.setItem(result.getItem());
            oneRequest.setItemDetail(result.getItemDetail());
            oneRequest.setUrl(result.getUrl());
            oneRequest.setQuantity(result.getQuantity());
            oneRequest.setOfferPrice(result.getOfferPrice());
            oneRequest.setRequestRemark(result.getRequestRemark());
            oneRequest.setCreatedAt(result.getCreatedAt());
            oneRequest.setUpdatedAt(result.getUpdatedAt());
            oneRequest.setImages(result.getImages());
            oneRequest.setCreatedBy(userRepository.findByUserId(result.getCreatedBy()));
            return oneRequest;
        } catch (Exception e) {
            throw new Exception("Cannot get one request");
        }
    }

    public void deleteRequest(String requestId, String userId) throws Exception {
        try {
            var request = this.requestRepository.getRequestByRequestId(requestId);
            logger.info("Deleting request with id: " + request);
            if (!request.getCreatedBy().equals(userId)) {
                throw new Exception("You are not the owner of this request");
            }
            this.requestRepository.deleteRequestByRequestId(requestId);
        } catch (Exception e) {
            throw new Exception("Cannot delete request");
        }
    }


//    public Page<AnotherRequestDto> getRequestsByCategoryAndLocationV2(String category, String location, int page) {
//        this.em.getCriteriaBuilder()
//    }
    }
