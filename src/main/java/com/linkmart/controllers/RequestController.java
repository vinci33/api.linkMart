package com.linkmart.controllers;

import com.linkmart.models.Request;
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
    public Request postRequest (@ModelAttribute Request requestForm, @RequestParam("image_file") MultipartFile file) {
        try{
            var request = requestService.postRequest(requestForm.getCreated_by(),requestForm.getLocation_id(),
                    requestForm.getCategory_id(),requestForm.getItem(),
                    file,requestForm.getUrl(),
                    requestForm.getQuantity(),requestForm.getRequest_remark(), requestForm.getOffer_price());
            return request;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(value = "/uploadfile")
    public String upload (@RequestParam("image") MultipartFile file) {
        try{
            var path = requestService.upload(file);
            return path;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
