package com.linkmart.controllers;

import com.linkmart.forms.RequestForm;
import com.linkmart.models.RequestModel;
import com.linkmart.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api")
public class RequestController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RequestService requestService;

    @PostMapping(value = "/request")
    public RequestModel postRequest (@ModelAttribute RequestModel requestModelForm,
                                    @RequestParam("image_file") MultipartFile file) {
        try{
            RequestModel request = requestService.postRequest(requestModelForm.getCreated_by(),
                    requestModelForm.getLocation_id(), requestModelForm.getCategory_id(),
                    requestModelForm.getItem(), requestModelForm.getUrl(),
                    requestModelForm.getQuantity(), requestModelForm.getRequest_remark(),
                    requestModelForm.getOffer_price(), file);
            return request;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

//    @PostMapping(value = "/uploadfile")
//    public String upload (@RequestParam("image") MultipartFile file) {
//        try{
//            var path = requestService.upload(file);
//            return path;
//        }catch (Exception e){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
}
