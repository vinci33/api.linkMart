package com.linkmart.services;

import com.google.gson.Gson;
import com.linkmart.dtos.OneRequestDto;
import com.linkmart.dtos.RequestDto;
import com.linkmart.models.ImageModel;
import com.linkmart.models.ItemDetailModel;
import com.linkmart.models.RequestModel;
import com.linkmart.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
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
    ImageRepository imageRepository;

    @Autowired
    S3Service s3Service;

    //route: POST: /api/request
    @Transactional
    public RequestModel postRequest(String userId, Integer locationId, Integer categoryId,
                                    String itemDetail, String item, String url,
                                    String quantity, String requestRemark, Integer offerPrice,
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

            if (requestRemark != null) {
                newRequest.setRequestRemark(requestRemark);
            }

            newRequest.makeRequestCase();//ulid
            if (offerPrice != null) {
                newRequest.setOfferPrice(offerPrice);
            }
            MultipartFile firstFile = null;
            List<ImageModel> images = new ArrayList<>();
            for (MultipartFile file: files) {
                String imagePath = s3Service.uploadFile(file);
                ImageModel image = new ImageModel();
                image.setImagePath(imagePath);
                image.setRequestId(newRequest.getRequestId());
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

    @Transactional
    public RequestModel postRequestClone(String userId, Integer locationId, Integer categoryId,
                                    String itemDetail, String item, String url,
                                    String quantity, String requestRemark, Integer offerPrice, List<String> urlImages,
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

            String firstFile = null;
            List<ImageModel> images = new ArrayList<>();
            for (String urlImage: urlImages) {
                ImageModel image = new ImageModel();
                image.setImagePath(urlImage);
                image.setRequestId(newRequest.getRequestId());
                images.add(image);
                if (firstFile == null) {
                    firstFile = urlImage;
                    newRequest.setPrimaryImage(urlImage); // Store the first file
                }
            }
            if (files != null)
            {
                for (MultipartFile file : files) {
                    String imagePath = s3Service.uploadFile(file);
                    ImageModel image = new ImageModel();
                    image.setImagePath(imagePath);
                    image.setRequestId(newRequest.getRequestId());
                    images.add(image);
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

    //route: GET: /request
    @Transactional
    public List<RequestDto> getAllRequest() throws Exception {
        try {
            var result = this.requestRepository.getAllRequest();
            return result;
        } catch (Exception e) {
            throw new Exception("Cannot get all request");
        }
    }

    //route: GET: /api/request (with is_active = true)
    @Transactional
    public List<RequestDto> getAllMyRequest(String userId) {
        try {
            var result = this.requestRepository.getAllRequestByUserId(userId);
            return result;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot get all my request");
        }
    }

    //route: GET: /request/{requestId}
    @Transactional
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

    //route: PUT: /api/request/{requestId}
    @Transactional
    public void deleteRequest(String requestId, String userId) throws Exception {
        try {
            var request = requestRepository.findCreatedByByRequestId(requestId);
            System.out.println("Deleting request with id: " + request);
            if (!request.equals(userId)) {
                throw new Exception("You are not the owner of this request");
            }
            requestRepository.updateRequestIsActiveByRequestId(requestId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Cannot delete request");
        }
    }

    //route: PUT: /api/request/update/{requestId}
    @Transactional
    public void updateRequest(String requestId, String userId, String itemDetail, String item, String url,
                              String quantity, String requestRemark, Integer offerPrice,
                              List<MultipartFile> files)
            throws Exception {
        try {
            var request = requestRepository.findCreatedByByRequestId(requestId);
            if (!request.equals(userId)) {
                throw new Exception("You are not the owner of this request");
            }
            var result = requestRepository.getRequestByRequestId(requestId);
            if (itemDetail != null) {
                Gson g = new Gson();
                ItemDetailModel itemDetailModel = g.fromJson(itemDetail, ItemDetailModel.class);
                result.setItemDetail(itemDetailModel);
            }
            if (item != null) {
                result.setItem(item);
            }
            if (url != null) {
                result.setUrl(url);
            }
            if (quantity != null) {
                result.setQuantity(quantity);
            }
            if (offerPrice != null) {
                result.setOfferPrice(offerPrice);
            }
            if (requestRemark != null) {
                result.setRequestRemark(requestRemark);
            }
            if (files != null) {
                List<ImageModel> images = new ArrayList<>();
                for (MultipartFile file: files) {
                    String imagePath = s3Service.uploadFile(file);
                    ImageModel image = new ImageModel();
                    image.setImagePath(imagePath);
                    image.setRequestId(result.getRequestId());
                    images.add(image);
                }
                result.setImages(images);
            }
            requestRepository.saveAndFlush(result);
        } catch (Exception e) {
            throw new Exception("Cannot update request");
        }
    }

    //route: GET: /api/request/history
    @Transactional
    public List<RequestDto> getAllMyRequestHistory(String userId) {
        try {
            var result = this.requestRepository.getAllRequestHistoryByUserId(userId);
            return result;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot get all my request history");
        }
    }

    @Transactional
    public void validateRequestId(String requestId) {
        var request = requestRepository.findRequestModelByRequestId(requestId);
        if (request == null) {
            throw new IllegalArgumentException("Invalid requestId");
        }
    }

    @Transactional
    public void updateRequestStatus(String requestId, Boolean hasOffer, Boolean isActive) {
        try {
            var request = requestRepository.findRequestModelByRequestId(requestId);
            if (request == null){
                throw new IllegalArgumentException("Request not found with id: " + requestId);
            }
            request.setHasOffer(hasOffer);
            request.setActive(isActive);
            requestRepository.saveAndFlush(request);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Cannot update request status"+ e.getMessage(), e);
        }
    }

//    public Page<AnotherRequestDto> getRequestsByCategoryAndLocationV2(String category, String location, int page) {
//        this.em.getCriteriaBuilder()
//    }
    }
