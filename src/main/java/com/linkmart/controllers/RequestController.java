package com.linkmart.controllers;

import com.linkmart.dtos.RequestDto;
import com.linkmart.filter.LogginFilter;
import com.linkmart.forms.RequestForm;
import com.linkmart.mappers.RequestMapper;
import com.linkmart.models.RequestModel;
import com.linkmart.services.RequestService;
import com.linkmart.repositories.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RequestController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RequestService requestService;

    @Autowired
    RequestRepository requestRepository;

//    @PostMapping(value = "/api/request", consumes = {"multipart/form-data"})
//    public RequestModel postRequest (@ModelAttribute RequestForm requestModelForm,
//                                     @RequestParam("itemDetail") String itemDetail,
//                                     @RequestParam("imageFile") List<MultipartFile> file) {
//        try{
//            RequestModel request = requestService.postRequest(requestModelForm.getCreatedBy(),
//                    requestModelForm.getLocationId(), requestModelForm.getCategoryId(),
//                    itemDetail,requestModelForm.getItem(), requestModelForm.getUrl(),
//                    requestModelForm.getQuantity(), requestModelForm.getRequestRemark(),
//                    requestModelForm.getOfferPrice(), file);
//            return request;
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
@PostMapping(value = "/api/request", consumes = {"multipart/form-data"})
public RequestModel postRequest (@ModelAttribute RequestForm requestModelForm,
                                 @RequestParam("itemDetail") String itemDetail,
                                 @RequestParam("imageFile") List<MultipartFile> file) {
    try{

        Map<String, Object> formDataString = new HashMap<>();
        formDataString.put("itemDetail", itemDetail);
        formDataString.put("item", requestModelForm.getItem());
        formDataString.put("url", requestModelForm.getUrl());
        formDataString.put("requestRemark", requestModelForm.getRequestRemark());


        Map<String, Object> formDataInteger = new HashMap<>();
        formDataInteger.put("quantity", requestModelForm.getQuantity());
        formDataInteger.put("offerPrice", requestModelForm.getOfferPrice());


        requestModelForm.processFormDataString(formDataString, "");
        requestModelForm.processFormDataInteger(formDataInteger, 0);

        RequestModel request = requestService.postRequest(requestModelForm.getCreatedBy(),
                requestModelForm.getLocationId(), requestModelForm.getCategoryId(),
                requestModelForm.getItemDetail(), requestModelForm.getItem(), requestModelForm.getUrl(),
                requestModelForm.getQuantity(), requestModelForm.getRequestRemark(),
                requestModelForm.getOfferPrice(), file);
        return request;
    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}

    @GetMapping(value = "/request")
    public List<RequestDto> getAllRequest () {
        try{
            return requestService.getAllRequest();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

//    @GetMapping(value = "/api/request")
//    public List<RequestDto> getAllMyRequest () {
//        try{
//
//            return requestService.getAllMyRequest();
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }

}
